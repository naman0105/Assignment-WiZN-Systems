var express = require('express');
var router = express.Router();
var mongo = require('mongodb').MongoClient;
var assert = require('assert');
var bodyParser = require('body-parser');
var fs = require("fs");
var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false })) 

var url = "mongodb://localhost:27017/"

router.get('/create', function (req, res) {
   res.render("createBook");
})

router.post('/submitbook', function (req, res) {
    var data = req.body;
    console.log(data);
    mongo.connect(url,function(err,db){
        assert.equal(null,err);
        var dbo = db.db("books");
        dbo.collection('book_list').insertOne(data,function(err,result){
            assert.equal(null,err);
            console.log("item inserted");
            db.close();
            res.redirect('/');
        })
    })
 })

router.get('/', function (req, res) {
    var data;
    mongo.connect(url,function(err,db){
        assert.equal(null,err);
        var dbo = db.db("books");
        dbo.collection('book_list').find({}).toArray(function(err, result){
            if(err) throw err;
            console.log(result);
            data = result;
            db.close();
            res.render("booklist",{data:data});
        }
    );
    })
 })

 router.get('/sorted', function (req, res) {
    var data;
    mongo.connect(url,function(err,db){
        assert.equal(null,err);
        var dbo = db.db("books");
        dbo.collection('book_list').find({}).sort({price:1}).toArray(function(err, result){
            if(err) throw err;
            console.log(result);
            data = result;
            db.close();
            res.render("booklist",{data:data});
        }
    );
    })
 })

 module.exports = router;