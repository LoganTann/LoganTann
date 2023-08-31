ig.module("game.feature.NyanIt").defines(function () {

          sc.NyanItFilter = ig.GameAddon.extend({

                    init: function() {
                              $("head").append('<link href="mods/CrossCode-NyanIt/nyan.css" rel="stylesheet"/>');
                              $("head").append('<script src="mods/CrossCode-NyanIt/nyan.js" />');
                              $("body").append('<audio id="nyanIntro" src="mods/CrossCode-NyanIt/intro.ogg"></audio><audio id="nyanLoop" src="mods/CrossCode-NyanIt/loop.ogg" preload="auto"></audio>');
                              $("#game").append('<div id="nyanContainer"><button id="nyanBtn" onclick="initNyan()">Click here to start Nyan Mod with sound</button></div>');
                              }
          });
          ig.addGameAddon(function() {
                    return (sc.nyanit = new sc.NyanItFilter());
                  });
});
