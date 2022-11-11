var check = function() {
         if (document.getElementById('mobile').value ==
         document.getElementById('alternateMobile').value) {
         document.getElementById('message').style.color = "red";
         document.getElementById('message').innerHTML = "Alternate mobile no shouldn't same as mobile no field";
         } else {
         document.getElementById('message').innerHTML = "";
         }
         }