 function selectChannels() {
     var channellist = document.getElementById('channellist');
     var channelbutton = document.getElementById('channelbtn');

     channellist.style.display = channellist.style.display = '';
     channelbutton.style.backgroundColor = channelbutton.style.backgroundColor = 'blue';


     var userlist = document.getElementById('userlist');
     var userbutton = document.getElementById('userbtn');

     userlist.style.display = userlist.style.display = 'none';
     userbutton.style.backgroundColor = userbutton.style.backgroundColor = 'transparent';
}

function selectUsers() {
    var channellist = document.getElementById('channellist');
    var channelbutton = document.getElementById('channelbtn');

    channellist.style.display = channellist.style.display = 'none';
    channelbutton.style.backgroundColor = channelbutton.style.backgroundColor = 'transparent';

    var userlist = document.getElementById('userlist');
    var userbutton = document.getElementById('userbtn');

    userlist.style.display = userlist.style.display = '';
    userbutton.style.backgroundColor = userbutton.style.backgroundColor = 'blue';
}