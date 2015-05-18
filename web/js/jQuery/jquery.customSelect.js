/*!
 * jquery.customSelect() - v0.3.0
 * http://adam.co/lab/jquery/customselect/
 * 2013-02-12
 *
 * Copyright 2013 Adam Coulombe
 * @license http://www.opensource.org/licenses/mit-license.html MIT License
 * @license http://www.gnu.org/licenses/gpl.html GPL2 License 
 */

(function ($) {
    'use strict';

    $.fn.extend({
        customSelect: function (options) {
            var defaults = {
                    customClass: null,
                    mapClass:    true,
                    mapStyle:    true
                },
                changed = function (el) {
                    var $select = $(el),
                        currentSelected = $select.find(':selected'),
                        customSelectSpan = $select.next(),
                        customSelectSpanInner = customSelectSpan.children(':first'),
                        html = currentSelected.html() || '&nbsp;';

                    customSelectSpanInner.html(html);

                    setTimeout(function () {
                        customSelectSpan.removeClass('customSelectOpen');
                    }, 60);
                };

            if (typeof document.body.style.maxHeight === 'undefined') {
                /* filter out <= IE6 */
                return this;
            }

            options = $.extend(defaults, options);

            return this.each(function () {
                var $select = $(this),
                    customSelectInnerSpan = $('<span class="customSelectInner" />'),
                    customSelectSpan = $('<span class="customSelect" />');

                customSelectSpan.append(customSelectInnerSpan);
                $select.after(customSelectSpan);

                if (options.customClass) {
                    customSelectSpan.addClass(options.customClass);
                }
                if (options.mapClass) {
                    customSelectSpan.addClass($select.attr('class'));
                }
                if (options.mapStyle) {
                    customSelectSpan.attr('style', $select.attr('style'));
                }

                $select
                    .addClass('hasCustomSelect')
                    .on('update', function () {
                        var selectBoxWidth = parseInt($select.outerWidth(), 10) -
                                (parseInt(customSelectSpan.outerWidth(), 10) -
                                    parseInt(customSelectSpan.width(), 10)),
                            selectBoxHeight = customSelectSpan.outerHeight();

                        changed(this);

                        if ($select.attr('disabled')) {
                            customSelectSpan.addClass('customSelectDisabled');
                        } else {
                            customSelectSpan.removeClass('customSelectDisabled');
                        }

                        customSelectSpan.css({
                            display: 'inline-block'
                        });

                        customSelectInnerSpan.css({
                            width:   'auto',
                            display: 'inline-block'
                        });

                        $select.css({
                            '-webkit-appearance': 'menulist-button',
                            'width':                /*customSelectSpan.outerWidth()*/'auto',
                            'position':             'absolute',
                            'opacity':              0,
                            'height':               /*selectBoxHeight*/25,
                            'fontSize':             customSelectSpan.css('font-size'),
                            'min-width':'215'
                        });
                    })
                    .on('change', function () {
                        customSelectSpan.addClass('customSelectChanged');

                        changed(this);
                    })
                    .on('keyup', function () {
                        $select.blur();
                        $select.focus();
                    })
                    .on('mousedown', function () {
                        customSelectSpan.removeClass('customSelectChanged').addClass('customSelectOpen');
                    })
                    .focus(function () {
                        customSelectSpan.removeClass('customSelectChanged').addClass('customSelectFocus');
                    })
                    .blur(function () {
                        customSelectSpan.removeClass('customSelectFocus customSelectOpen');
                    })
                    .hover(function () {
                        customSelectSpan.addClass('customSelectHover');
                    }, function () {
                        customSelectSpan.removeClass('customSelectHover');
                    })
                    .trigger('update');
            });
        }
    });
})(jQuery);
