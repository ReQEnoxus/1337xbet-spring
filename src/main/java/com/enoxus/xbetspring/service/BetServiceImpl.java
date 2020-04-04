package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.BetDto;
import com.enoxus.xbetspring.dto.BetViewDto;
import com.enoxus.xbetspring.dto.MatchDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.Bet;
import com.enoxus.xbetspring.entity.Match;
import com.enoxus.xbetspring.entity.Prediction;
import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.exceptions.BetCreatingException;
import com.enoxus.xbetspring.repositories.BetRepository;
import com.enoxus.xbetspring.repositories.MatchRepository;
import com.enoxus.xbetspring.repositories.UserRepository;
import com.enoxus.xbetspring.util.BetHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private BetHelper betHelper;

    @Autowired
    private MatchService matchService;

    @Override
    public void createBetForUser(BetDto betDto, UserDto userDto) throws BetCreatingException {
        betRepository.getAllByOwnerId(userDto.getId()).stream()
                .filter(b -> b.getMatch().getId().equals(betDto.getMatchId()))
                .findAny().ifPresent(b -> {
            throw new BetCreatingException("Вы уже поставили ставку на этот матч");
        });

        User user = userRepository.findById(userDto.getId()).<BetCreatingException>orElseThrow(() -> {
            throw new BetCreatingException("Пользователь не найден"); // не должно выкидываться в принципе
        });

        Match match = matchRepository.findById(betDto.getMatchId()).<BetCreatingException>orElseThrow(() -> {
            throw new BetCreatingException("Матч не найден"); // не должно выкидываться в принципе
        });

        Prediction prediction = Prediction.values()[betDto.getPrediction()];

        if (user.getBalance() < betDto.getAmount()) {
            throw new BetCreatingException("Не хватает денег");
        } else if (betDto.getAmount() < 1) {
            throw new BetCreatingException("Размер ставки должен быть положительным");
        } else if (new Date().after(match.getDate())) {
            throw new BetCreatingException("Матч уже начался или был сыгран");
        } else if (!"Not Started".equals(match.getStatus())) {
            throw new BetCreatingException("Матч уже начался или был сыгран");
        } else {
            user.setBalance(user.getBalance() - betDto.getAmount());
            userRepository.save(user);

            Bet bet = Bet.builder()
                    .active(true)
                    .amount(betDto.getAmount())
                    .coefficient(betHelper.calculateCoefficients(match.getHomeTeam().getId(), match.getAwayTeam().getId()).get(prediction))
                    .match(match)
                    .owner(user)
                    .won(false)
                    .build();
            betRepository.save(bet);
        }
    }

    @Override
    public void verifyAndCloseBetsOnFinishedMatches() {
        List<Bet> bets = betRepository.getAllByActiveTrue();

        for (Bet bet : bets) {
            Match match = matchRepository.findById(bet.getMatch().getId()).orElseThrow(IllegalStateException::new);
            if (match.getStatus().equals("Match Finished")) {
                Prediction matchResult;
                if (match.getGoalsAwayTeam() > match.getGoalsHomeTeam()) {
                    matchResult = Prediction.AWAY;
                } else if (match.getGoalsHomeTeam() > match.getGoalsAwayTeam()) {
                    matchResult = Prediction.HOME;
                } else {
                    matchResult = Prediction.DRAW;
                }
                if (bet.getPrediction().toString().equals(matchResult.toString())) {
                    //Bet won
                    bet.setWon(true);
                    User owner = bet.getOwner();
                    owner.setBalance(owner.getBalance() + bet.getAmount() * bet.getCoefficient());
                    bet.setActive(false);
                    userRepository.save(owner);
                    betRepository.save(bet);
                } else {
                    //Bet lost
                    bet.setWon(false);
                    bet.setActive(false);
                    betRepository.save(bet);
                }
            } else if (match.getStatus().equals("Match Cancelled")) {
                bet.setWon(true);
                User owner = bet.getOwner();
                owner.setBalance(owner.getBalance() + bet.getAmount() * bet.getCoefficient());
                bet.setActive(false);
                userRepository.save(owner);
                betRepository.save(bet);
            }
        }
    }

    @Override
    public List<BetViewDto> getAllBetsOfUser(Long userId) {
        return betRepository.getAllByOwnerId(userId)
                .stream()
                .map(bet -> BetViewDto.builder()
                        .amount(bet.getAmount())
                        .coefficient(bet.getCoefficient())
                        .match(matchService.getMatchById(bet.getMatch().getId()).orElseThrow(IllegalArgumentException::new))
                        .active(bet.isActive())
                        .build())
                .collect(Collectors.toList());

    }
}
