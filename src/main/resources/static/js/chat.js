var CHAT = (function(chat){
    $(function(){
        $.ajaxSetup({contentType: 'application/json'});
        init();
        
    });

    function init(){
    	attachListenerToSendBtn();
        displayAllMessages();
        displayAllUsers();
        autoCompleteSender();
        autoCompleteRecvi();
        getMessagesFromRemote();
        deleteAllMessages();
    };

    function displayAllMessages(){
        $.get('message', function( data ) {
            for(var i=0;i<data.length;i++){
                appendMessage(data[i]);
            }
        });
    };
    function displayAllUsers(){
        $.get('api/users', function( data ) {
            for(var i=0;i<data.length;i++){
                appendUser(data[i]);
            }
        });
    };
    function attachListenerToSendBtn(){
        $('#send-btn').click(function(){
            message = {
                senderId: $('#sender-id').val(),
                receiverId: $('#receiver-id').val(),
                body: $('#content').val()
            };
            $.post('message', JSON.stringify(message), function(data){
                console.log('message successfully sent ' + JSON.stringify(data));
//                appendMessage(data);
            }, 'json');
        });
    };
    function autoCompleteSender(){
    	$(function () {
    	   
    	    $.getJSON("api/users", function (data) {
    	       
    	        $("#sender-id").autocomplete({
    	            source: data
    	        });
    	    });
    	});
    	
    };
    function autoCompleteRecvi(){
    	$(function () {
    
    	    $.getJSON("api/users", function (data) {
    	       
    	        $("#receiver-id").autocomplete({
    	            source: data
    	        });
    	    });
    	});
    	
    };
    function getMessagesFromRemote(){
    	
    	    setInterval(function(){ 
    	    	
    	    	
    	    		$.get('message/remote', function( data ) {
    	             for(var i=0;i<data.length;i++){
    	                 appendMessage(data[i]);
    	                 console.log("getFromRemoteSUcces")
    	             }
    	         });
    	    
    	    
    	    
    	    }, 3000);
  
    };
 

    function appendMessage(message){
        if(message.receiverId){
            $('.list-group').append('<li class="list-group-item list-group-item-success">'
                + '[' + message.receiverId + '] '
                + message.body
                + '<div style="float:right">' + formatDate(message.created)
                + '</div></li>');
        } else{
            $('.list-group').append('<li class="list-group-item list-group-item-info">'
                + '[' + message.senderId + '] '
                + message.body
                + '<div style="float:right">' + formatDate(message.created)
                + '</div></li>');
        }
    };
   

    function appendUser(user){
       
            $('.users').append('<li class="list-group-item">'
                + user
                
                + '</li>');
    };
    function deleteAllMessages(){
        
        $("#delete-all-btn").click(function(){
     	   $.get("message/delete",function(){
     		   location.reload();
     		   
     	   });
     	   
        });
 };
    
 

    function formatDate(epochDate){
        return new Date(epochDate);
    };

    return {};
})(CHAT || {});