let btn1=document.getElementById('btn1');
let btn2 = document.getElementById('btn2');
let myPosition = document.getElementById('myPosition');

btn1.addEventListener("mouseover",function(event){
    event.target.style.background="#40E0D0";
});

btn1.addEventListener("mouseout",function(event){
    event.target.style.background="black";
});

btn2.addEventListener("mouseover",function(event){
    event.target.style.background="#40E0D0";
});

btn2.addEventListener("mouseout",function(event){
    event.target.style.background="black";
});

myPosition.addEventListener("mouseover",function(event){
    event.target.style.background="red";
});

myPosition.addEventListener("mouseout",function(event){
    event.target.style.background="white";
});