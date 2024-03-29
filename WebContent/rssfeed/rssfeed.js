/* * rssFeed - Your rss feed on your website * * http://noprobweb.com/rss_feed_flux_jquery.php * * Version : 1.6 * * Copyright (c) 2013 NoProbWeb (http://www.noprobweb.com) * * Licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) * * Built on top of the jQuery library * http://jquery.com * */
jQuery.rssFeed = function (urlFeed, options) {
    rssFeedOpt = jQuery.extend({
      //  crossFile: "rssfeed/rssfeed.php",
        errorMessage: "no Rss Feed, Flux rss manquant",
        errorMode: "off",
        nextInterval: 4000,
        lang: "en",
        header: "on",
        displayimg: "on",
        heightpict: 30,
        div: "rssfeed",
        position: "relative"
    }, options);
    if (rssFeedOpt.lang == "fr") {
        var linkName = "Lire la Suite";
        var localMode = 'mode local (sans http)';
        var distantMode = 'mode inter domaine';
        var load = 'Chargement en cours...';
    } else {
        var linkName = "Read More";
        var localMode = 'localhost mode';
        var distantMode = 'Cross-domain mode';
        var load = 'Loading Data...';
    } if (rssFeedOpt.header == "on") {
    	alert(" ess");
        $("#" + rssFeedOpt.div).replaceWith('<div id="' + rssFeedOpt.div + '"><div id="titre"></div><div id="feed"><div id="loading" align="center"><img src="rssfeed/loading.gif" alt="loading" />' + load + ' </div></div>');
    } else {
        $("#" + rssFeedOpt.div).replaceWith('<div id="' + rssFeedOpt.div + '"><div id="feed"><div id="loading" align="center"><img src="rssfeed/loading.gif" alt="loading" />' + load + ' </div></div>');
    } if (!urlFeed && urlFeed.length == 0) {
        $("#loading").show();
        $("#feed").append(rssFeedOpt.errorMessage);
        return false;
    } else {
        if (urlFeed.substr(0, 7) == 'http://' && rssFeedOpt.crossFile != '') {
            if (rssFeedOpt.errorMode == 'on') {
                alert(distantMode);
            }
            urlFeed = 'http://' + urlFeed.substr(7);
        } else if (urlFeed.substr(0, 8) == 'https://' && rssFeedOpt.crossFile != '') {
            if (rssFeedOpt.errorMode == 'on') {
                alert(distantMode);
            }
            urlFeed = 'https://' + urlFeed.substr(8);
        } else {
            if (urlFeed.substr(0, 7) != 'http://') {
                if (rssFeedOpt.errorMode == 'on') {
                    alert(distantMode);
                }
            }
        }
    }
    $("#loading").show();
    $.ajax({
        type: "POST",
        url: rssFeedOpt.crossFile,
        data: {
            feed: urlFeed
        },
        dataType: "xml",
        success: parseXml
    });
    var i = 0;
    var titre = new Array();
    var description = new Array();
    var pubDate = new Array();
    var link = new Array();

    function parseXml(xml) {
        $(xml).find("channel").each(function () {
            if ($(this).children("image").children("url").text() != "") {
                if (rssFeedOpt.header == "on") {
                    if ($(this).children("link").text() != "") {
                        textTitre = '<div id="titre"><a href="' + $(this).children("link").text() + '" target="_blank"><img src="' + $(this).children("image").children("url").text() + '" /></a><a href="' + $(this).children("link").text() + '" target="_blank">' + $(this).children("title").text() + '</a></div>';
                    } else {
                        textTitre = '<div id="titre"><img src="' + $(this).children("image").children("url").text() + '" />' + $(this).children("title").text() + '</div>';
                    }
                }
            } else {
                if ($(this).children("link").text() != "") {
                    textTitre = '<div id="titre" align="center"><a href="' + $(this).children("link").text() + '" target="_blank">' + $(this).children("title").text() + '</a></div>';
                } else {
                    textTitre = '<div id="titre" align="center">' + $(this).children("title").text() + '</div>';
                }
            } if (rssFeedOpt.header == "on") {
                $("#titre").replaceWith(textTitre);
            }
        });
        $(xml).find("item").each(function () {
            $("#loading").show();
            titre[i] = $(this).find("title").text();
            description[i] = $(this).find("description").text();
            pubDate[i] = $(this).find("pubDate").text();
            link[i] = $(this).find("link").text();
            i++;
        });
        nb_feed = titre.length;
        j = 0;
        animation = setInterval(showDiv, rssFeedOpt.nextInterval);

        function timePassed(Time) {
            var S = 1000,
                M = 60 * S,
                H = 60 * M,
                J = 24 * H,
                W = 7 * J,
                Mo = 4 * W,
                Y = 12 * M;
            var t = new Array(S, M, H, J, W, Mo, Y);
            var pref = new Array('il y a environ', 'about');
            var suffrsg = new Array('seconde', 'minute', 'heure', 'jour', 'semaine', 'mois', 'année');
            var suffrpl = new Array('secondes', 'minutes', 'heures', 'jours', 'semaines', 'mois', 'années');
            var sufensg = new Array('second ago', 'minute ago', 'hour ago', 'day', 'week ago', 'month ago', 'year ago');
            var sufenpl = new Array('seconds ago', 'minutes ago', 'hours ago', 'days ago', 'weeks ago', 'months ago', 'years ago');
            for (var i = 0; i <= t.length; i++) {
                if (Time - t[i] <= 0) {
                    if (rssFeedOpt.lang == 'fr') {
                        if (Math.round(Time / t[i - 1]) == 1) var suf = suffrsg[i - 1];
                        else var suf = suffrpl[i - 1];
                        var prefixe = pref[0];
                    } else {
                        if (Math.round(Time / t[i - 1]) == 1) var suf = sufensg[i - 1];
                        else var suf = sufenpl[i - 1];
                        var prefixe = pref[1];
                    }
                    return prefixe + ' ' + Math.round(Time / t[i - 1]) + ' ' + suf;
                }
            }
        }

        function stop() {
            clearInterval(animation);
        }

        function restart() {
            animation = setInterval(showDiv, rssFeedOpt.nextInterval);
        }

        function delimg(string) {
            var debstring;
            var endstring;
            while (string.indexOf('<img', 0) != -1) {
                debstring = string.substring(0, string.indexOf("<img", 0));
                var posimg = string.indexOf("/>", 0) + 2;
                endstring = string.substring(posimg);
                string = debstring + endstring;
            }
            return string;
        }

        function setimg(string) {
            var debstring;
            var endstring;
            var imgstring;
            if (string.indexOf("<img", 0) != -1) {
                debstring = string.substring(0, string.indexOf("<img", 0));
                var posimg = string.indexOf("/>", 0) + 2;
                var posdegimg = string.indexOf("<img", 0);
                imgstring = string.substring(posdegimg, posimg);
                endstring = string.substring(posimg);
                string = debstring + '<p>' + imgstring + '</p>' + endstring;
            }
            return string;
        }

        function calcHeight() {
            $('#resume').css('height', 'auto');
            var heightresume = $('#resume').height();
            if (rssFeedOpt.displayimg == "on") {
                heightresume += rssFeedOpt.heightpict;
            }
            $('#resume').css('height', '0px');
            return heightresume;
        }

        function onreload() {
            var heightresume = calcHeight();
            $('#feed').mouseenter(function () {
                if ($('#resume').height() >= 0) {
                    var tmpHeight = $('#resume').height();
                    $('#resume').stop(true, true);
                    $('#resume').height(tmpHeight + "px");
                    $('#resume').animate({
                        height: heightresume + "px"
                    }, 1000).show();
                    $('.a').hide('slow');
                }
            });
            $('#feed').mouseleave(function () {
                if ($('#resume').height() > 0) {
                    $('#resume').animate({
                        height: "0px"
                    }, 1000).hide('slow');
                    var tmpHeight = $('#resume').height();
                    $('.a').show('slow');
                }
            });
        }

        function showDiv() {
            if (j < nb_feed - 1) {
                $("#loading").hide();
                var des = description[j];
                var tit = titre[j];
                if (rssFeedOpt.displayimg == "on") des = setimg(des);
                else des = delimg(des);
                var datePost = new Date(pubDate[j]);
                var dateActuel = new Date();
                var diff = timePassed(dateActuel.getTime() - datePost.getTime());
                var feedDiv = '<div id="feed"><div id="title">' + tit + '<div id="time">' + diff + '</div><div class="a"><b>Powered by </b><a href="http://www.noprobweb.com" target="_blank">NoProbWeb</a></div></div><div id="resume">' + des + '<div id="link"><br /><a href="' + link[j] + '" target="_blank">' + linkName + '</a></div><div class="b"><b>Powered by </b><a href="http://www.noprobweb.com" target="_blank">NoProbWeb</a></div></div></div>';
                $('#feed').replaceWith(feedDiv);
                if ($('.a').length || $('.b').length) {} else {
                    alert("Error : don't modify the announce");
                    stop();
                }
                $('.a').css({
                    'font-size': 'xx-small',
                    'text-align': 'right'
                });
                $('.b').css({
                    'font-size': 'xx-small',
                    'text-align': 'right'
                });
                if (rssFeedOpt.position == "absolute" || rssFeedOpt.position == "relative" || rssFeedOpt.position == "fixed" || rssFeedOpt.position == "Inherit") {
                    $("#feed").css('position', rssFeedOpt.position);
                } else {
                    $("#feed").css('position', 'relative');
                }
                j++;
                $('#feed').hover(stop, restart);
            } else {
                if (j == nb_feed - 1) {
                    j = 0;
                }
            }
            onreload();
        }
    }
}