// timer.js
// author: hongh
// dagte : 2005-02-03

function Timer(hour,minute,up,down) {
  this.hour = hour;
  this.minute = minute;
  this.upButton = up;
  this.downButton = down;
  this.hour.onkeydown = hourKeydown;
  this.minute.onkeydown = minuteKeydown;
  this.upButton.onmousedown = toUp;
  this.upButton.onmouseup = setActive;
  this.downButton.onmousedown = toDown;
  this.downButton.onmouseup = setActive;
  this.activeElement;
}

//�����¼�
function toUp(){
  this.activeElement = document.activeElement;
  var txt = parseInt(this.activeElement.value) + 1

  if(this.activeElement.onkeydown == minuteKeydown) {
      
      if(validateMinute(txt))
          this.activeElement.value = parseInt(this.activeElement.value) + 1;
  }
  if(this.activeElement.onkeydown == hourKeydown) {
      if(validateHour(txt)) {
         this.activeElement.value = parseInt(this.activeElement.value) + 1;
      }
  }
}

//�����¼�
function toDown() {
   this.activeElement = document.activeElement;

  var txt = parseInt(this.activeElement.value) - 1
  if(this.activeElement.onkeydown == minuteKeydown) {
      if(validateMinute(txt))
          this.activeElement.value = parseInt(this.activeElement.value) - 1;
  }
  if(this.activeElement.onkeydown == hourKeydown) {
      if(validateHour(txt)) {
         this.activeElement.value = parseInt(this.activeElement.value) - 1;
      }
  }
}

//���ü���״̬
function setActive(){

  this.activeElement.setActive();
  this.activeElement.select();
}

//Сʱ�������
function hourKeydown() {
   var txt = event.srcElement;
   var myhour = txt.value + String.fromCharCode(event.keyCode);
   
   if(event.keyCode == 32) {
      event.returnValue = false;
      return;
   }
   
   if(event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39)  return;

   if(validateHour(myhour)) return;
   event.returnValue = false;
}

//��֤Сʱ�Ϸ���
function validateHour(myhour) {
   
   if(isNaN(myhour)) return false;
   if(parseInt(myhour) <0 || parseInt(myhour) > 23) return false;
   return true;
}

//�ж��Ƿ�Ϊ��ֵ��
function isNumber(code){
   if(code > 47 && code < 58) return true;
   return false;
}

//�����������
function minuteKeydown() {
   var txt = event.srcElement;
   var myminute = txt.value + String.fromCharCode(event.keyCode);
   if(event.keyCode == 32) {
      event.returnValue = false;
      return;
   }
   
   if(event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39) return;
   
   if(validateMinute(myminute)) return;
   event.returnValue = false;
}

//��֤���ӺϷ���
function validateMinute(myMinute) {
   
   if(isNaN(myMinute)) return false;
   if(parseInt(myMinute) <0 || parseInt(myMinute) > 59) return false;
   return true;
}

