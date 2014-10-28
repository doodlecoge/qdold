(function($) {
	var svgns = "http://www.w3.org/2000/svg";

    var createEle = function(ns, el) {
        return document.createElementNS(ns, el);
    };

	var defaults = {
		"line": {
			"stroke": "black",
			"stroke-width": 1
		},
		"circle": {
			"fill": "blue"
		},
		"text": {
			"font-family": "Courier New",
			"font-size": "12"
		},
		"rect": {
			"fill": "#ccc",
			"stroke": "black"
		}
	};
	
	// set container to transformation group,
	// if has any
	var getContainer = function(svg) {
		return svg.find('g:first')[0] || svg[0];
	};
	
	var methods = {
		'setDefaults': function(defs) {
			$.extend(true, defaults, defs);
		},
		'setCustomViewPort': function(tx, ty, sx, sy) {
//			var obj = document.createElementNS(svgns, "g");
            var obj = createEle(svgns, "g");
			obj.setAttributeNS(null, "transform", 
					"translate(" + tx + ", " + ty
					+ ") scale(" + sx + ", " + sy + ")");
			
			this[0].appendChild(obj);
		},
		'clear': function() {
			var g = getContainer(this);
			while(g.lastChild) {
				g.removeChild(g.lastChild);
			}
		},
		'drawLine': function(x1, y1, x2, y2, attrs) {
			attrs = $.extend({}, defaults["line"] || {}, attrs || {});
			
//			var obj = document.createElementNS(svgns, "line");
            var obj = createEle(svgns, "line");

			$.each(attrs, function(k, v) {
				obj.setAttributeNS(null, k, v);
			});
			
			// set after other attributes,
			// in case override
			obj.setAttributeNS(null, "x1", x1);
			obj.setAttributeNS(null, "y1", y1);
			obj.setAttributeNS(null, "x2", x2);
			obj.setAttributeNS(null, "y2", y2);
			
			getContainer(this).appendChild(obj);
			return obj;
		},
		
		"drawCircle": function(cx, cy, r, attrs) {
//			var obj = document.createElementNS(svgns, "circle");
            var obj = createEle(svgns, "circle");

			attrs = $.extend({}, defaults["circle"] || {}, attrs || {});
			
			$.each(attrs, function(k, v) {
				obj.setAttributeNS(null, k, v);
			});

			obj.setAttributeNS(null, "cx", cx);
			obj.setAttributeNS(null, "cy", cy);
			obj.setAttributeNS(null, "r", r);
			
			getContainer(this).appendChild(obj);
			return obj;
		},
		
		"drawText": function(x, y, text, attrs) {
//			var obj = document.createElementNS(svgns, "text");
            var obj = createEle(svgns, "text");

			attrs = $.extend({}, defaults["text"] || {}, attrs || {});
			
			$.each(attrs, function(k, v) {
				obj.setAttributeNS(null, k, v);
			});

			obj.setAttributeNS(null, "x", x);
			obj.setAttributeNS(null, "y", y);
			
			obj.appendChild(document.createTextNode(text));
			
			getContainer(this).appendChild(obj);
			return obj;
		},
		
		"drawRect": function(x, y, w, h, attrs) {
//			var obj = document.createElementNS(svgns, "rect");
            var obj = createEle(svgns, "rect");

			attrs = $.extend({}, defaults["rect"] || {}, attrs || {});
			
			$.each(attrs, function(k, v) {
				obj.setAttributeNS(null, k, v);
			});

			obj.setAttributeNS(null, "x", x);
			obj.setAttributeNS(null, "y", y);
			obj.setAttributeNS(null, "width", w);
			obj.setAttributeNS(null, "height", h);
			
			getContainer(this).appendChild(obj);
			return obj;
		}
	};
	
	
	$.fn.svg = function() {
		var args = arguments;
		
		if (args.length < 1)
			return;

		var arg0 = args[0];
		
		if (methods[arg0]) {
			args = Array.prototype.slice.call(args, 1);
			return methods[arg0].apply(this, args);
		} else {
			$.error('No arguments!');
		}
	};
})(jQuery);