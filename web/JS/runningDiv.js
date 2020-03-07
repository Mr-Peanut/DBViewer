/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function AddRunningDiv() {
$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(document).height() ,position:"fixed",top:"10px",left:"10px"}).prependTo("body");
$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候...").prependTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(document).height() - 45) / 2 });
};
function MoveRunningDiv() {
$("div[class='datagrid-mask']").remove();
$("div[class='datagrid-mask-msg']").remove();
};
