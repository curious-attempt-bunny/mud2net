$(document).ready(function() {

var minTimestamp = null;
var maxTimestamp = null;

var pointsData = [];
var staminaData = [];
var magicData = [];

$('span').each(function(k,tag) {
  var el = $(tag);
  var timestamp = el.attr("t");
  if (timestamp) {
    timestamp = parseInt(timestamp)
    if (minTimestamp == null) {
      minTimestamp = timestamp;
      maxTimestamp = timestamp;
    } else {
      maxTimestamp = timestamp;
    }
  }
  if (el.attr("MAGIC")) {
    if (magicData.length > 0) {
      var last = magicData[magicData.length-1];
      magicData.push({x: maxTimestamp, y: last.y});
    }
    magicData.push({x: maxTimestamp, y: parseInt(el.attr("MAGIC"))});
  }
  if (el.attr("STAMINA")) {
    if (staminaData.length > 0) {
      var last = staminaData[staminaData.length-1];
      staminaData.push({x: maxTimestamp, y: last.y});
    }
    staminaData.push({x: maxTimestamp, y: parseInt(el.attr("STAMINA"))});
  }
  if (el.attr("POINTS")) {
    if (pointsData.length > 0) {
      var last = pointsData[pointsData.length-1];
      pointsData.push({x: maxTimestamp, y: last.y});
    }
    pointsData.push({x: maxTimestamp, y: parseInt(el.attr("POINTS"))});
  }
});

var vis = d3.select('#visualisation');
var WIDTH = $('svg').width();
var HEIGHT = $('svg').height();
var MARGINS = {
      top: 5,
      right: 25,
      bottom: 5,
      left: 5
    };
var xRange = d3.scale.linear().range([MARGINS.left, WIDTH - MARGINS.right]).domain([minTimestamp, maxTimestamp]);
var magicRange = d3.scale.linear().range([HEIGHT - MARGINS.top, MARGINS.bottom]).domain([0,d3.max(magicData, function(d) { return d.y; })]);
var staminaRange = d3.scale.linear().range([HEIGHT - MARGINS.top, MARGINS.bottom]).domain([0,d3.max(staminaData, function(d) { return d.y; })]);
var pointsRange = d3.scale.linear().range([HEIGHT - MARGINS.top, MARGINS.bottom]).domain([d3.min(pointsData, function(d) { return d.y; }),d3.max(pointsData, function(d) { return d.y; })]);

var magicLine = d3.svg.line().x(function(d) { return xRange(d.x); }).y(function(d) { return magicRange(d.y); }).interpolate('linear');
var staminaLine = d3.svg.line().x(function(d) { return xRange(d.x); }).y(function(d) { return staminaRange(d.y); }).interpolate('linear');
var pointsLine = d3.svg.line().x(function(d) { return xRange(d.x); }).y(function(d) { return pointsRange(d.y); }).interpolate('linear');

var chartXoffsets = [];
$('span').each(function(k,tag) {
  var el = $(tag);
  if (el.attr('t')) {
    var position = el.offset().top;
    var timestamp = parseInt(el.attr('t'));
    var x = xRange(timestamp);
    while (x>chartXoffsets.length) {
      chartXoffsets.push(position);
    }
  }
});
$('svg').mousemove(function(e) {
  var x = e.offsetX;
  if (x < chartXoffsets.length) {
    window.scrollTo(0, chartXoffsets[x] - 150);
  }
});

vis.append('svg:path')
  .attr('d', magicLine(magicData))
  .attr('stroke', 'blue')
  .attr('stroke-width', 1)
  .attr('fill', 'none');

vis.append('svg:path')
  .attr('d', staminaLine(staminaData))
  .attr('stroke', 'red')
  .attr('stroke-width', 1)
  .attr('fill', 'none');

vis.append('svg:path')
  .attr('d', pointsLine(pointsData))
  .attr('stroke', 'white')
  .attr('stroke-width', 1)
  .attr('fill', 'none');

});