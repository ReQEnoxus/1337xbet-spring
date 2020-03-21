package com.enoxus.xbetspring.util;


import com.enoxus.xbetspring.entity.CoefficientSet;
import com.enoxus.xbetspring.entity.Prediction;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BetHelper {
    private static DecimalFormat df = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.US));

    static {
        df.setRoundingMode(RoundingMode.FLOOR);
    }

    public static Map<Prediction, Double> calculateCoefficients(int teamHome, int teamAway) {
        Map<Prediction, Double> coefficients = new HashMap<>();

        double difference = Math.min(teamHome, teamAway) / (double) Math.max(teamHome, teamAway);

        if (teamHome < teamAway) {
            coefficients.put(Prediction.HOME, Double.parseDouble(df.format(1 + 1 / (1 / difference + 1))));
            coefficients.put(Prediction.AWAY, Double.parseDouble(df.format(1 + 1 / (1 / difference + 1) * 1 / difference)));
            coefficients.put(Prediction.DRAW, Double.parseDouble(df.format(1 + difference * 0.75)));
        } else {
            coefficients.put(Prediction.AWAY, Double.parseDouble(df.format(1 + 1 / (1 / difference + 1))));
            coefficients.put(Prediction.HOME, Double.parseDouble(df.format(1 + 1 / (1 / difference + 1) * 1 / difference)));
            coefficients.put(Prediction.DRAW, Double.parseDouble(df.format(1 + difference * 0.75)));
        }

        return coefficients;
    }

    public static CoefficientSet coefficientSet(int teamHome, int teamAway) {
        Map<Prediction, Double> coeffs = calculateCoefficients(teamHome, teamAway);
        return new CoefficientSet(coeffs.get(Prediction.HOME), coeffs.get(Prediction.AWAY), coeffs.get(Prediction.DRAW));
    }
}