// JsDebug.js

// newFunction
function JsDebug(level) {
   /*debug,error,normal*/
   this.level = level;
}

JsDebug.debug = function f_debug(message,lbl){

   if(this.getLevelCode() < 2) {
       var mess = '';
   
       var mess = mess + "ҳ��·����" + document.URL + "\n\n";
   
       if(this.debug.caller != null){
           mess = mess + "���ú�����" + this.debug.caller + "\n\n";
       }
       mess = mess + "������Ϣ��" + message;
       if(lbl == null){
          alert(mess);
       }
       else{
          lbl.innerText = mess;
       }
       
   }
}

JsDebug.error = function f_error(message,lbl){
   
   if(this.getLevelCode() <= 2) {
       var mess = '';
   
       var mess = mess + "ҳ��·����" + document.URL + "\n\n";
   
       if(this.error.caller != null){
           mess = mess + "���ú�����" + this.error.caller + "\n\n";
       }
       mess = mess + "������Ϣ��" + message;
       if(lbl == null){
          alert(mess);
       }
       else{
          lbl.innerText = mess;
       }
       
   }

}

JsDebug.getLevelCode = function f_getLevelCode(){
   switch(JsDebug.level){
      case 'debug':
          return 1;
      case 'error':
          return 2;
   }
}


JsDebug.prototype.debug = function f_debug(message,lbl){

   if(this.getLevelCode() < 2) {
       var mess = '';
   
       var mess = mess + "ҳ��·����" + document.URL + "\n\n";
   
       if(this.debug.caller != null){
           mess = mess + "���ú�����" + this.debug.caller + "\n\n";
       }
       mess = mess + "������Ϣ��" + message;
       if(lbl == null){
          alert(mess);
       }
       else{
          lbl.innerText = mess;
       }
       
   }
}

JsDebug.prototype.error = function f_error(message,lbl){
   
   if(this.getLevelCode() <= 2) {
       var mess = '';
   
       var mess = mess + "ҳ��·����" + document.URL + "\n\n";
   
       if(this.error.caller != null){
           mess = mess + "���ú�����" + this.error.caller + "\n\n";
       }
       mess = mess + "������Ϣ��" + message;
       if(lbl == null){
          alert(mess);
       }
       else{
          lbl.innerText = mess;
       }
       
   }

}

JsDebug.prototype.getLevelCode = function f_getLevelCode(){
   switch(this.level){
      case 'debug':
          return 1;
      case 'error':
          return 2;
   }
}
