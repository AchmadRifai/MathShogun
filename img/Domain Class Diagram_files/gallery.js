(function($) {
    function NavigationMove(e){
        e.preventDefault();
        var $this = $(this);
        var $GalleryBlock           = $this.closest('.home-gallery-wrap');
        var $MainContainer          = $GalleryBlock.find('.main-gallery');
        var $CurrentMainItem        = $GalleryBlock.find('.main-item.gallery-item');
        var IsNext                  = $this.hasClass('nav-next');
        
        var $NextItemLists          = $GalleryBlock.find('.gallery-grid');
        var NextItemSize            = $NextItemLists.attr('data-column-size');
        var $galleryNav             = $GalleryBlock.find('.gallery-nav');
        
        if($NextItemLists.length == 0)
        {
            return;
        }
        if(IsNext)
        {
            var $NextItem               = $NextItemLists.find('.gallery-item').first();
        }
        else
        {
            var $NextItem               = $NextItemLists.find('.gallery-item').last();
        }
        
        
        var promise1 = $CurrentMainItem.animate({'opacity':0},200);
        var promise2 = $NextItem.animate({'opacity':0},200);
        
        $.when(promise1,promise2).done(function(){
            $CurrentMainItem
                .removeClass('main-item')
                .addClass('col-sm-' + NextItemSize)
                .addClass('col-xs-12')
                .detach();
        
            if(IsNext)
            {
                $CurrentMainItem.appendTo($NextItemLists);
            }
            else
            {
                $CurrentMainItem.prependTo($NextItemLists);
            }
                
        
            $NextItem
                .addClass('main-item')
                .removeClass('col-sm-' + NextItemSize)
                .removeClass('col-xs-12')
                .removeClass('item-first')
                .removeClass('item-last')
                .detach()
                .prependTo($MainContainer);
        
            $NextItemLists.find('.gallery-item').each(function(i,obj){
                var $GalleryItem = $(obj);
                $GalleryItem
                    .removeClass('item-first')
                    .removeClass('item-last')
                    .addClass( (i % (12 / NextItemSize) + 1 === 1 ? 'item-first' : '') )
                    .addClass( (i % (12 / NextItemSize) + 1 === (12 / NextItemSize) ? 'item-last' : '') );
            });
            
        
            $CurrentMainItem.animate({'opacity':1},200);
            $NextItem.animate({'opacity':1},200);
            
            $galleryNav.detach().appendTo($NextItem.find('.box-image'));
        });

    }
    /**
     * Convert into masonry
     */
    function masonrying(){
        if($('#gallery-container .gallery-item').length)
        {
            $('#gallery-container').masonry({
                itemSelector    : '.gallery-item'
                ,columnWidth    : 235
                ,fitWidth     : true
            });
        }
    }

    $(document).ready(function() {
        $('.home-gallery-wrap').on('click','.gallery-nav',NavigationMove);
        $('.home-gallery-wrap .box-image a').not('.gallery-nav').B2014Fancybox();
        masonrying();
    });

})(jQuery);