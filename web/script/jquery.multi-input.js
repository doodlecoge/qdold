(function($) {
	var methods = {
		'setup': function() {
			this.html('');
			this.removeClass('ipt-wp').addClass('ipt-wp');

			var ipt = $('<div class="ipt"></div>');
            ipt.css('width', '12px');
            ipt.css('overflow', 'hidden');
            ipt.css('padding-left', '2px');
			this.append(ipt);

			var txt = $('<input type="text" />');
            txt.css('border', '0');
            txt.addClass("transparent");

			ipt.append(txt);

			var wth = $('<span class="ipt-width">WW</span>');
            wth.css('display', 'none');
			ipt.append(wth);

			this.click(function() {
				var $this = $(this);
				$this.find("input").focus();
			});

			txt.keypress(function(e) {
				var $this = $(this);
                var ipt = $this.parent();
				var ipt_w = ipt.find(".ipt-width");


				ipt_w.html($this.val() + 'WW');
                var w = ipt_w.width() + 12;
				$this.width(w);
                ipt.width(w);
                ipt.css('overflow', 'hidden');
			});

			txt.keydown(function(e) {
				if (e.keyCode != 8 || $(this).val() != '')
					return;
				var $this = $(this);
				$this.parent().parent().find(".ipt-item:last").remove();
			});

			
		},
		
		'autocomplete': function(setting) {
			var txt = this.find("input");
			txt.autocomplete(setting);
		},
		
		'addItem': function(val, id) {
			var item = $('<div class="ipt-item"></div>');
			item.html(val);
			
			var hid = $('<input type="hidden" value="' + id + '" />');
            var close = $('<span class="close"></span> ')

            item.append(hid);
            item.append(close);

			item.insertBefore(this.find(".ipt"));
		}
	};

	$.fn.multiInput = function() {
		var args = arguments;
		var arg0 = args[0];

		if (methods[arg0]) {
			args = Array.prototype.slice.call(args, 1);
			return methods[arg0].apply(this, args);
		} else {
			$.error('No arguments!');
		}

		//setup.apply(this, arguments);
	};
})(jQuery);