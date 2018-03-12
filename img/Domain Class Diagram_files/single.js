(function($){
    function LastUpdateShow(e){
        e.preventDefault();
        $('#modified-date-detail').fadeIn();
    }
    $(document).ready(function(){
        $('#single-container').on('click','#modified-date-container a',LastUpdateShow);
    });
})(jQuery);