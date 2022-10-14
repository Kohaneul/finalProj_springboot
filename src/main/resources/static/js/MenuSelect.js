
    let btn1=document.getElementById('btn1');

    btn1.addEventListener("mouseover",function(event){
        event.target.style.background="#40E0D0";
    });

    btn1.addEventListener("mouseout",function(event){
        event.target.style.background="black";
    });

   let menu_Id = document.getElementById('mId').value;

	function radioCheck(menuId){
		menu_Id= menuId;
	}



    function closeAndSendParam()
    {
    if(menu_Id == null || menu_Id == "" ||menu_Id == "undefined"){
        alert('선택하지 않았습니다');
        window.location.reload();
    }
    else { open("/board/select?menuId="+menu_Id); self.close(); }
    }


	function setChildText(){
		let popupWidth = 500;
		let popupHeight = 400;
		let left = Math.ceil((window.screen.width-popupWidth)/2);
		let top = Math.ceil((window.screen.height-popupHeight)/2);;
		console.log("choose : "+choose);
		let pickup_id = document.getElementById('place_name').value;
		let url = "/board/select/restaurant/menu?menuId="+choose;
		openWin = window.open(url,"parentForm2","width="+popupWidth+",height="+popupHeight+",left="+left+",top="+top+"resizable=yes")

	}