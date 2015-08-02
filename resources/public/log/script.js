var script;

script = document.createElement('script');
script.src = "http://code.jquery.com/jquery-2.1.4.js";
document.getElementsByTagName('head')[0].appendChild(script);

script = document.createElement('script');
script.src = "https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js";
script.charset = "utf-8";
document.getElementsByTagName('head')[0].appendChild(script);

script = document.createElement('script');
script.src = "http:/mud2.net/log/timeline.js";
document.getElementsByTagName('head')[0].appendChild(script);