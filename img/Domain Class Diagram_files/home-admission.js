(function($){
    function popdialog(e){
        e.stopPropagation();
        var $this       = $(this);
        var $li         = $this.closest('.the-item');
        var $container  = $this.closest('#future-student');
        var $dialog     = $li.find('.pop-dialog');

        if($dialog.length < 1)
        {
            return true;
        }
        var $otherDialog    = $container.find('.pop-dialog').not($dialog);
        
        e.preventDefault();
        var height = $dialog.height();
        
        
        $otherDialog
            .fadeOut('fast')
            .removeClass('show');

        
        if($dialog.hasClass('show'))
        {
            return $dialog
                .fadeOut()
                .removeClass('show');
        }
        
        $dialog
            .stop()
            .fadeIn()
            .addClass('show');
    }
    function closedialog(e){
        
        var $dialog = $('#future-student .the-item .pop-dialog');
        $dialog
            .fadeOut()
            .removeClass('show');
    }
    
    function colorPopItem(e){
        var $this   = $(this);
        var $prev   = $this.prev();
        if($prev.hasClass('dialog-item'))
        {
            if(e.type === 'mouseover')
            {
                $prev.addClass('hover');
            }
            else
            {
                $prev.removeClass('hover');
            }            
        }
    }
    function popdialogStopBuble(e){
        e.stopPropagation();
    }
    $(document).ready(function(){
        $('body').on('click',closedialog);
        $('#future-student .the-item').on('click',' > a',popdialog);
        $('#future-student .pop-dialog').on('mouseover','.dialog-item',colorPopItem);
        $('#future-student .pop-dialog').on('mouseout','.dialog-item',colorPopItem);
        $('#future-student .pop-dialog').not('.not-bubbling').on('click',popdialogStopBuble);
    });
})(jQuery);