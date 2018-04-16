var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var fs = require("fs");
var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false })) 

router.get('/create', function (req, res) {
   res.render("createBook");
})

router.post('/submitbook', function (req, res) {
    console.log("we reachd here" + req.body.name);
    // var data = JSON.stringify(req.body);
    var data = req.body;
    var data1 = fs.readFileSync(__dirname + "/" + "../books.json");
    data1 = JSON.parse(data1);
    console.log("datafromfile : "+data1);
    data1["book" + req.body.date] = data;
    console.log("datafromfile : "+data1);
    fs.writeFileSync(__dirname + "/" + "../books.json",JSON.stringify(data1));
    res.redirect(200,'/');
    // res.send("hello");
    // res.redirect(307,"/");
 })

router.get('/', function (req, res) {
    fs.readFile( __dirname + "/" + "../books.json", 'utf8', function (err, data) {
        console.log( data );
        res.render("booklist",{data:data});
    });
 })


 module.exports = router;