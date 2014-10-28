var rptBaseUrl = "http://ta.cisex.com.cn";
var url = rptBaseUrl + "/tasche/reports/viewGenericReport.action";

function makeProjectLink(pc, bn) {
    return url + "?projectCode=" + pc + "&buildNumber=" + bn;
}

function isVmlSupported() {
    if (typeof supportsVml.supported == "undefined") {
        var a = document.body.appendChild(document.createElement('div'));
        a.innerHTML = '<v:shape id="vml_flag1" adj="1" />';
        var b = a.firstChild;
        b.style.behavior = "url(#default#VML)";
        supportsVml.supported = b ? typeof b.adj == "object" : true;
        a.parentNode.removeChild(a);
    }
    return supportsVml.supported
}

function isSvgSupported() {
    return document.implementation.hasFeature("http://www.w3.org/TR/SVG11/feature#Shape", "1.0") ||
        ("http://www.w3.org/TR/SVG11/feature#Shape", "1.1");
}

function isCanvasSupported() {
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}


function ts() {
    var d = new Date();
    return (d - 0) * 1000 + d.getMilliseconds();
}


function resizeIfrmSize(cat) {
    if (!cat) return;

    var loc = window.location;
    var id = loc.toString().match(/widgetId=([0-9]+)$/)[1];

    var ifrm = $("iframe[src$=" + cat + "\\?widgetId\\=" + id + "]", parent.document);

//    var w = ifrm.parent().width();
    var h = $(document.body).height();

//    alert(h);

    ifrm.height(h);
//    ifrm.width(w);
}


function getContrastL($hexcolor) {
    var arr = $hexcolor.split('');

    $r = parseInt(arr[0], 16) * 10 + parseInt(arr[1], 16);
    $g = parseInt(arr[2], 16) * 10 + parseInt(arr[3], 16);
    $b = parseInt(arr[4], 16) * 10 + parseInt(arr[5], 16);


    $l = ($r * 0.2126) + ($g * 0.7152) + ($b * 0.0722);

    return ($l >= 128) ? 'black' : 'white';
}


function newLinkItem(url, val, bClose) {


    var link = $('<a href="' + url + '" class="new-link-item">' + val + '</a>');

    if (bClose === false) return link;

    var close = $('<span>&times;</span>');
    link.append(close);

    return link;
}