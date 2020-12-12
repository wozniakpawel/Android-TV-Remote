const express = require('express');
const app = express();
const server = require('http').Server(app);
const exec = require('child_process').exec;

server.listen(80);

// app.use(express.static('.'));
app.get('/', function (req, res) {
  res.sendFile(__dirname + '/index.html');
});

app.get('/reboot', (req, res) => {
  res.send('rebooting rpi')
  exec('sudo reboot');
});

app.get('/pwr', (req, res) => pressKey('POWER', res));
app.get('/src', (req, res) => pressKey('MEDIA', res));
app.get('/menu', (req, res) => pressKey('MENU', res));
app.get('/mute', (req, res) => pressKey('MUTE', res));
app.get('/up', (req, res) => pressKey('UP', res));
app.get('/down', (req, res) => pressKey('DOWN', res));
app.get('/left', (req, res) => pressKey('LEFT', res));
app.get('/right', (req, res) => pressKey('RIGHT', res));
app.get('/ok', (req, res) => pressKey('OK', res));
app.get('/voldn', (req, res) => pressKey('VOLUMEDOWN', res));
app.get('/volup', (req, res) => pressKey('VOLUMEUP', res));

function pressKey(key, res) {
  exec(`irsend send_once myremote KEY_${key}`);
  res.send('OK');
}
