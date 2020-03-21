var totalMatches = 0;
var currentOffset = 0;
var currentLimit = 10;
var scrollTimer;

$(document).ready(function () {
    $.get("/matches/count", {matchNumber: document.querySelector("#dateSelector").selectedIndex})
        .done(function (data) {
            totalMatches = data;
        });
    $(window).on("scroll", function () {
        clearTimeout(scrollTimer);

        scrollTimer = setTimeout(function () {
            afterScroll()
        }, 500);
    });

    function afterScroll() {
        if ($(window).scrollTop() + window.innerHeight > $(document).height() - 100 && currentLimit < totalMatches) {
            currentOffset = currentLimit;
            currentLimit += 10;
            $.get("/matches", {
                limit: currentLimit,
                query: document.querySelector("#searchInput").value,
                date: document.querySelector("#dateSelector").selectedIndex,
                offset: currentOffset
            }).done(function (data) {
                $("#matches").append(data);
            })
        }
    }

    $("#searchInput").on('input', function () {
        if (document.querySelector("#searchInput").value === "") {
            getNewDataAndClearOffset()
        }
        else {
            getSearchData()
        }
    });

    $("#dateSelector").change(function () {
        getNewDataAndClearOffset();
    })
});

function getNewDataAndClearOffset() {
    currentOffset = 0;
    currentLimit = 10;

    getMatchesCount();

    $.get("/matches", {
        limit: currentLimit,
        query: document.querySelector("#searchInput").value,
        date: document.querySelector("#dateSelector").selectedIndex,
        offset: currentOffset
    }).done(function (data) {
        $("#matches").html(data);
    })
}

function getSearchData() {
    $.get("/matches", {
        limit: totalMatches,
        query: document.querySelector("#searchInput").value,
        date: document.querySelector("#dateSelector").selectedIndex,
        offset: 0
    }).done(function (data) {
        $("#matches").html(data);
    })
}

function getMatchesCount() {
    $.get("/matches/count", {matchNumber: document.querySelector("#dateSelector").selectedIndex})
        .done(function (data) {
            totalMatches = data;
            console.log(data);
        });
}