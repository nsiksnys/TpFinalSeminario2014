$(document).ready(function() {
    $(function() {
	$(':checkbox').click(checked);
	$(":checkbox:checked").each(checked);
    });
    
    function checked() {
        if ($(this).attr('checked') == true) {
    	$(this).parent().attr('style', 'background:#ff0000;');
        } else {
    	$(this).parent().removeAttr('style');
    	// o $(this).parent().attr('style','background:#ff0000;') y elejis el color si no esta seleccionado
        }
    }
});
