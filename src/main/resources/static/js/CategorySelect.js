
   let category_Id = document.getElementById('categoryId').value;

	function radioCheck(categoryId){
		category_Id= categoryId;
	}



    function closeAndSendParam()
    {
    if(category_Id == null || category_Id == "" ||category_Id == "undefined"){
        alert('선택하지 않았습니다');
        window.location.reload();
    }
    else { open("/board/select/restaurant?categoryId="+category_Id); self.close(); }
    }


	function setChildText(){
		let popupWidth = 500;
		let popupHeight = 400;
		let left = Math.ceil((window.screen.width-popupWidth)/2);
		let top = Math.ceil((window.screen.height-popupHeight)/2);;
		console.log("choose : "+choose);
		let pickup_id = document.getElementById('place_name').value;
		let url = "/board/select/restaurant?categoryId="+choose;
		openWin = window.open(url,"parentForm2","width="+popupWidth+",height="+popupHeight+",left="+left+",top="+top+"resizable=yes")

	}