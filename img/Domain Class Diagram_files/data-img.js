(function($){
$.fn.dataImg=function(a){function b(a){var b=$(window).width();return a.data(b>d.med?"lrg":b<=d.med&&b>d.sml?"med":"sml")}function c(){e.each(function(){var a=$(this),c=b(a);void 0!=c&&(a.is("img")?a.attr("src",c):a.css("background-image","url("+c+")"))})}var d=$.extend({sml:400,med:800,lrg:1e3,resize:!1},a),e=$(this);c(),1==d.resize&&$(window).resize(function(){c()})};
})(jQuery);