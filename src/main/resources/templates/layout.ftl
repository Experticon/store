<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        .navbar ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        .navbar ul li {
            float: left;
        }

        .navbar ul li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .navbar ul li a:hover {
            background-color: #111;
        }
    </style>
</head>
<body>
    <#include "header.ftl">
    <div class="content">
        <@block name="content"/>
    </div>
</body>
</html>