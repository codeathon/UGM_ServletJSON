/*
 * Login Part
 */
$(document).ready(function(){
    var Input = $('input[name=username]');
    var default_value = Input.val();

    $(Input).focus(function() {
        if($(this).val() == default_value)
        {
             $(this).val("");
        }
    }).blur(function(){
        if($(this).val().length == 0) /*Small update*/
        {
            $(this).val(default_value);
        }
    });
})
function pwdFocus() {
        $('#Fakepassword').hide();
        $('#Password').show();
        $('#Password').focus();
}
function pwdBlur() {
    if ($('#Password').attr('value') == '') {
            $('#Password').hide();
            $('#Fakepassword').show();
    }
}

/*
 * Add user form
 */
$(document).ready(function(){
    var Input = $('input[name=email]');
    var default_value = Input.val();

    $(Input).focus(function() {
        if($(this).val() == default_value)
        {
             $(this).val("");
        }
    }).blur(function(){
        if($(this).val().length == 0) /*Small update*/
        {
            $(this).val(default_value);
        }
    });
})
function pwdFocus_adduser_retype() {
    $('#Retype_Fakepassword').hide();
    $('#Retype_Password').show();
    $('#Retype_Password').focus();
}
function pwdBlur_adduser_retype() {
	if ($('#Retype_Password').attr('value') == '') {
	        $('#Retype_Password').hide();
	        $('#Retype_Fakepassword').show();
	}
}