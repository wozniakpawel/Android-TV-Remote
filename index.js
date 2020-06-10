var express = require('express');
var app = express();
var server = require('http').Server(app);

app.use(express.static('.'));

server.listen(80);

const exec = require('child_process').exec;

app.get('/', function (req, res) {
  res.sendFile(__dirname + '/index.html');
});

app.get('/reboot', function (req, res) {
  res.send('rebooting rpi')
  exec('sudo reboot');
});

app.get('/pwr', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_POWER');
});

app.get('/src', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_MEDIA');
});

app.get('/menu', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_MENU');
});

app.get('/mute', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_MUTE');
});

app.get('/up', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_UP');
});

app.get('/down', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_DOWN');
});

app.get('/left', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_LEFT');
});

app.get('/right', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_RIGHT');
});

app.get('/ok', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_OK');
});

app.get('/voldn', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_VOLUMEDOWN');
});

app.get('/volup', function (req, res) {
  res.send('OK')
  exec('irsend send_once myremote KEY_VOLUMEUP');
});
