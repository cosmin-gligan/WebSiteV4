/*
[Core Script]
Project: Chic Classic - Bootstrap Forms
*/
(function ($) {
    "use strict";
    // INPUTS
    var inputs = document.querySelectorAll('input[type="text"], textarea, input[type="textarea"], input[type="password"], input[type="email"], input[type="number"]');
    // INPUTS UNDERLINE ANIMATION
    if (inputs) {
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].previousElementSibling.classList.add('floating-label');
            inputs[i].addEventListener('focusin', function () {
                this.previousElementSibling.classList.add('label-active');
            })
            inputs[i].addEventListener('focusout', function () {
                if (!(this.value == '')) {
                    this.classList.add('input-active');
                }
                else if (this.value == '') {
                    this.classList.remove('input-active');
                    this.previousElementSibling.classList.remove('label-active');
                }
                else {
                    this.previousElementSibling.classList.remove('label-active');
                }
            })
        }
    }
    // REQUIRED INPUTS
    if (inputs) {
        for (var m = 0; m < inputs.length; m++) {
            if (inputs[m].hasAttribute('required')) {
                inputs[m].previousElementSibling.classList.add('label-required');
            }
        }
    }
}(jQuery));