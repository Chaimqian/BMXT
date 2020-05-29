/**
 *  页面加载等待页面
 *
 *
 */
var height = window.screen.height-250;
var width = window.screen.width;
var leftW = 300;
if(width>1200){
    leftW = 500;
}else if(width>1000){
    leftW = 350;
}else {
    leftW = 100;
}

var _html = "<div id='loading' style='position:absolute;left:0;width:100%;height:"+height+"px;top:0;background:#E0ECFF;opacity:0.8;filter:alpha(opacity=80);'>\
 <div style='position:absolute;  cursor1:wait;left:"+leftW+"px;top:200px;width:auto;height:36px;padding:12px 5px 10px 30px;\
 background:#fff url(img/load.gif) no-repeat scroll 5px 10px;border:2px solid #ccc;color:#000;'>\
 正在加载，请等待...\
 </div></div>";

window.onload = function(){
    var _mask = document.getElementById('loading');
    _mask.parentNode.removeChild(_mask);
}


document.write(_html);


/**
 * ajax的等待加载
 */

function beforeSend() {
    this.layerIndex=layer.load("加载中...", {shade: [0.5, '#393D49'],content: '<br><p style="white-space:nowrap">数据正在获取中,请您耐心等待...</p>'});
}
function endSend(){
    layer.close(this.layerIndex);
}

/**
 *成功的显示框
 */
function showMessage(title,msg){
    $.messager.show({
        title:title,
        msg:msg,
        showType:'slide',
        style:{
            right:'',
            top:document.body.scrollTop+document.documentElement.scrollTop,
            bottom:''
        }
    });
}

/**
 * 设置两个时间的先后，选择开始时间后，结束时间只能选择开始时间以后的时间
 * @param startTime  开始时间的控件id
 * @param endTime    结束时间的控件id
 */
function startAndEndTime(startTime,endTime) {
    $(startTime).datebox({
        onSelect : function(beginDate){
            $(endTime).datebox().datebox('calendar').calendar({
                validator: function(date){
                    return beginDate<date;//<=
                }
            });
        }
    });
}



