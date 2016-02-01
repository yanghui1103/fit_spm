// Decroate.js

// Ϊ�ؼ������ʽ
function Decrator(control){
   this.control = control;
}
//���ÿؼ���classֵ
Decrator.prototype.attachClass = function f_attachClass(tclass){
    this.control.className = tclass;
}
//���ÿؼ���ʽ
Decrator.prototype.attachStyle = function f_attachStyle(style){
    this.control.style.cssText = style.style.cssText;
}

//��ʽ��
function Style(control){
    this.control = control;
    this.style = this.control.style;
}
Style.prototype.fontSize = function f_fontSize(fontSize){
    this.style.fontSize = fontSize;
    return this;
}
Style.prototype.font = function f_font(font){
    this.style.font = font;
    return this;
}
Style.prototype.display = function f_display(display){
    this.style.display = display;
    return this;
}
Style.prototype.cursor = function f_cursor(cursor){
    this.style.cursor = cursor;
    return this;
}
Style.prototype.border = function f_border(border){
    this.style.border = border;
    return this;
}
Style.prototype.background = function f_background(background){
    this.style.background = background;
    return this;
}

Style.prototype.backgroundColor  = function f_backgroundColor (backgroundColor ){
    this.style.backgroundColor = backgroundColor ;
    return this;
}


