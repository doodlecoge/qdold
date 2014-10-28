(function ($) {

    var AllProjectCodes = null;
    var InputMessage = 'Click to Add Project Codes!';
    var AddItemPostHander = null;


    function newInput() {
        var ipt = $('<input type="text" />');

        ipt.css('font-size', '12px');
        ipt.css('font-family', 'Courier New');
        ipt.css('border', '0');
        ipt.css('padding', '0');

        return ipt;
    }


    function showInput() {
        var $this = $(this);

        var ipt = $this.find('input:text');

        if (ipt.length == 0) {
            ipt = newInput();
            $this.append(ipt);

            ipt.blur(function() {
                $this.find('span[iam=msg]').show();
                $(this).hide();
            });


            ipt.focus(function() {
                $this.find('span[iam=msg]').hide();
                $(this).show();
            });

            ipt.keypress(function(e) {
                var ch = String.fromCharCode(e.which);
                var iamwidth = $this.find("span[iam=width]");

                iamwidth.html($(this).val() + "WW");

                var w = iamwidth.width();

                $(this).width(w);
            });

            ipt.autocomplete({
                minLength: 2,
                source: function(request, response) {
                    var re = new RegExp(request.term);

                    var a = $.grep(AllProjectCodes, function(v, i) { return re.test(v); });
                    response(a);
                },
                focus: function() {
                    return false;
                },
                select: function(event, ui) {
                    addItem($this, ui.item.value);
                    $(this).val('');
                    return false;
                }
            });
        }

        ipt.show();
        ipt.focus();
        ipt.select();
    }


    function addItem(container, val) {
        var lns = container.find("a");
        var len = lns.length;

        // duplicate detect
        for(var i = 0; i < len; i++) {
            var ln = lns.get(i);

            var pc = $(ln).contents().get(0).nodeValue;
            if(val == pc) return;
        }


        var el = $('<a href="javascript:void(0)" class="new-link-item">' + val + '</a>');
        var close = $('<span>&times;</span>');

        el.append(close);

        var lst = container.find("a:last");

        if(lst.length == 0) container.prepend(el);
        else el.insertAfter(lst);


        if(AddItemPostHander && (typeof AddItemPostHander == "function"))
            AddItemPostHander();
    }


    /*
     * handle box click event:
     * 1. remove project code;
     * 2. show input element;
     */
    function boxClick(e) {
        var $this = $(this);

        var el = $(e.target);

        // remove item
        if (el.html() == '\u00D7') {
            el.parent().remove();
            return;
        }

        // show input element
        showInput.call($this, e);
    }


    /*
     * methods will be called by plugin entry
     */
    var methods = {
        setSource: function (all_pcs) {
            AllProjectCodes = all_pcs;
        },

        init: function (pcs) {
            var $this = $(this);

            if(pcs) {
                $.each(pcs, function(i, v) {
                    addItem($this, v);
                });
            }

            var iammsg = $('<span iam="msg" style="color: gray;">' + InputMessage + '</span>');
            var iamwidth = $('<span iam="width" style="font-size: 12px; font-family: \'Courier New\'; visibility: hidden; position: absolute; z-index: -1000; left: 0;">WW</span>');


            $this.append(iammsg);
            $this.append(iamwidth);

            $this.click(boxClick);
        },

        getProjectCodes: function() {
            var $this = $(this);

            var lns = $this.find("a");

            var pcs = $.map(lns, function(ln) {
                var pc = $(ln).contents().get(0).nodeValue;
                return pc;
            });

            return pcs;
        },

        setAddItemPostHandler: function(func) {
            AddItemPostHander = func;
        }
    }

    /*
     * add as jQuery plugin
     */
    $.fn.ProjectCodesInputBox = function () {
        var args = arguments;
        var arg0 = args[0];

        if (methods[arg0]) {
            args = Array.prototype.slice.call(args, 1);
            return methods[arg0].apply(this, args);
        } else {
            $.error('No arguments!');
        }
    }

})(jQuery);

