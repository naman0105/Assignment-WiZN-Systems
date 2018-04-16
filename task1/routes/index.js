var express = require('express');
var restapi = require('../REST_API/api');
var router = express.Router();

router.use('/books', require('../REST_API/api'));


router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

module.exports = router;
