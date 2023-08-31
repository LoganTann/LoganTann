/* NyanIt ! CrossCode mod
   Copyright (c) 2019 ShinProg
   Under the MIT license, see LICENSE file for more info
*/


//left init values for generated nyan
const initLeft = ["0px","-50px","-73px","-100px","-178px","-300px"];
//height of generated nyan
const initHeight = [0, 28, 41, 56, 100, 168];
var started=false;


function randint(min, max) {
    /* selects a random natural number between max and min */
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}

function addNyan() {
    /*This will add a nyan each 1-6 seconds, if there is less than 20*/
    if (document.getElementById("nyanContainer").childElementCount<20 && started){
        let size = randint(1,5);
        let screenHeight = document.getElementById("game").offsetHeight - initHeight[size];
        $("#nyanContainer").append(`<div class="size${size}" style="top: ${randint(0,screenHeight)}px; left: ${initLeft[size]}"></div>`);
    }
    clearInterval(timer);
    timer = setInterval(addNyan, randint(1000,6000));
}

function playNyan(){
    /*Intro music finished, let's move all nyans*/
    document.getElementById("nyanLoop").loop = true;
    $('#nyanLoop').trigger('play');
    
    //update nyan loop; loops every 300ms
    (function ascyncLoop() {
        if (document.getElementById("nyanContainer").childElementCount>0){
            $("#nyanContainer div").each(function(i,e){
                // add 6 to int value of ( selected element's left position without "px")
                let left = parseInt(e.style.left.slice(0,-2)) + 6;
                let screenWidth = document.getElementById("game").offsetWidth;
                if (left < screenWidth){
                $(e).css({
                    "top": e.style.top,
                    "left": left+"px"
                });
                } else {
                    document.getElementById("nyanContainer").removeChild(e);
                }
            });
        }
        setTimeout(ascyncLoop, 300);
    })();
}


var timer = setInterval(addNyan, 500);

setTimeout(function(){
    /*starts the mod after 5 secs even if the button is not clicked (but the sound migth not be played)*/
    if (!started){
        started = true;
        document.getElementById("nyanContainer").removeChild(document.getElementById("nyanBtn"));
        playNyan();
    }
}, 5000);

function initNyan(){
    /* play the intro and when finished, starts all*/
    started = true;
    document.getElementById("nyanContainer").removeChild(document.getElementById("nyanBtn"));
    $('#nyanIntro').trigger('play');
	$('#nyanIntro').bind('ended', playNyan);
}