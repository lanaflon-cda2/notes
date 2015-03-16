PrimeFaces.widget.OverlayPanel=PrimeFaces.widget.BaseWidget.extend({init:function(a){this._super(a);this.content=this.jq.children("div.ui-overlaypanel-content");this.target=PrimeFaces.expressions.SearchExpressionFacade.resolveComponentsAsSelector(this.cfg.target);this.cfg.my=this.cfg.my||"left top";this.cfg.at=this.cfg.at||"left bottom";this.cfg.showEvent=this.cfg.showEvent||"click.ui-overlaypanel";this.cfg.hideEvent=this.cfg.hideEvent||"click.ui-overlaypanel";this.cfg.dismissable=(this.cfg.dismissable===false)?false:true;if(this.cfg.showCloseIcon){this.closerIcon=$('<a href="#" class="ui-overlaypanel-close ui-state-default" href="#"><span class="ui-icon ui-icon-closethick"></span></a>').appendTo(this.jq)}this.bindEvents();if(this.cfg.appendToBody){this.jq.appendTo(document.body)}this.setupDialogSupport()},bindEvents:function(){var f=this;this.target.data("primefaces-overlay-target",this.id).find("*").data("primefaces-overlay-target",this.id);if(this.cfg.showEvent===this.cfg.hideEvent){var d=this.cfg.showEvent;this.target.on(d,function(g){f.toggle()})}else{var a=this.cfg.showEvent+".ui-overlaypanel",e=this.cfg.hideEvent+".ui-overlaypanel";this.target.off(a+" "+e).on(a,function(g){if(!f.isVisible()){f.show()}}).on(e,function(g){if(f.isVisible()){f.hide()}})}f.target.off("keydown.ui-overlaypanel keyup.ui-overlaypanel").on("keydown.ui-overlaypanel",function(i){var h=$.ui.keyCode,g=i.which;if(g===h.ENTER||g===h.NUMPAD_ENTER){i.preventDefault()}}).on("keyup.ui-overlaypanel",function(i){var h=$.ui.keyCode,g=i.which;if(g===h.ENTER||g===h.NUMPAD_ENTER){f.toggle();i.preventDefault()}});if(this.cfg.showCloseIcon){this.closerIcon.on("mouseover.ui-overlaypanel",function(){$(this).addClass("ui-state-hover")}).on("mouseout.ui-overlaypanel",function(){$(this).removeClass("ui-state-hover")}).on("click.ui-overlaypanel",function(g){f.hide();g.preventDefault()})}if(this.cfg.dismissable){var c="mousedown."+this.id;$(document.body).off(c).on(c,function(h){if(f.jq.hasClass("ui-overlay-hidden")){return}var g=$(h.target);if(f.target.is(g)||f.target.has(g).length>0){return}var i=f.jq.offset();if(h.pageX<i.left||h.pageX>i.left+f.jq.outerWidth()||h.pageY<i.top||h.pageY>i.top+f.jq.outerHeight()){f.hide()}})}var b="resize."+this.id;$(window).off(b).on(b,function(){if(f.jq.hasClass("ui-overlay-visible")){f.align()}})},toggle:function(){if(!this.isVisible()){this.show()}else{this.hide()}},show:function(){if(!this.loaded&&this.cfg.dynamic){this.loadContents()}else{this._show()}},_show:function(){var a=this;this.align();this.jq.removeClass("ui-overlay-hidden").addClass("ui-overlay-visible").css({display:"none",visibility:"visible"});if(this.cfg.showEffect){this.jq.show(this.cfg.showEffect,{},200,function(){a.postShow()})}else{this.jq.show();this.postShow()}},align:function(){var b=this.jq.css("position")=="fixed",c=$(window),a=b?"-"+c.scrollLeft()+" -"+c.scrollTop():null;this.jq.css({left:"",top:"","z-index":++PrimeFaces.zindex}).position({my:this.cfg.my,at:this.cfg.at,of:document.getElementById(this.cfg.target),offset:a})},hide:function(){var a=this;if(this.cfg.hideEffect){this.jq.hide(this.cfg.hideEffect,{},200,function(){a.postHide()})}else{this.jq.hide();this.postHide()}},postShow:function(){if(this.cfg.onShow){this.cfg.onShow.call(this)}this.applyFocus()},postHide:function(){this.jq.removeClass("ui-overlay-visible").addClass("ui-overlay-hidden").css({display:"block",visibility:"hidden"});if(this.cfg.onHide){this.cfg.onHide.call(this)}},setupDialogSupport:function(){var a=this.target.parents(".ui-dialog:first");if(a.length==1){this.jq.css("position","fixed");if(!this.cfg.appendToBody){this.jq.appendTo(document.body)}}},loadContents:function(){var b=this,a={source:this.id,process:this.id,update:this.id,params:[{name:this.id+"_contentLoad",value:true}],onsuccess:function(e,c,d){PrimeFaces.ajax.Response.handle(e,c,d,{widget:b,handle:function(f){this.content.html(f);this.loaded=true}});return true},oncomplete:function(){b._show()}};PrimeFaces.ajax.Request.handle(a)},isVisible:function(){return this.jq.hasClass("ui-overlay-visible")},applyFocus:function(){this.jq.find(":not(:submit):not(:button):input:visible:enabled:first").focus()}});