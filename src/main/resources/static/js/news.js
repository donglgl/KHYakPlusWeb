/**
 *  뉴스 스라이더 테스트 스크립트
 */

$(function()
{
    var ticker = function()
    {
        setTimeout(function(){
            $('#ticker td:first').animate( {marginTop: '-20px'}, 400, function()
            {
                $(this).detach().appendTo('ul#ticker').removeAttr('style');
            });
            ticker();
        }, 3000);
    };
    ticker();
});
