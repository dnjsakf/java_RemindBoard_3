/**
 * 
 */


// 댓글 목록을 가져오기위한 필요 데이터

let commentList = document.getElementById("comment-list");
const url = location.href;
const pattern = /id=(\d+)/g;

let INIT = {
	"id": pattern.exec(url)[1],
	"page": 1
}

// 첫번째 댓글 목록 가져오기!
getComments( INIT.id, INIT.page );

// 댓글 목록 가져오기
function getComments( id, page ){
	console.log("[AJAX GET COMMENTS]", id, page, typeof $ == 'function');
	if( typeof $ == 'function'){
		$.ajax(
			{
				url: "http://localhost:8080/board3/board/comment/list",
				type: "GET",
				data: {id, page},
				error: function( error ) {
					console.log("[error]",error);
				},
				success: function( result ) {
					console.log("[result]", result)
					
					// createCommentsList
					if( result.comments != null ){
						if( commentList.childElementCount > 0 ){
							commentList.children[0].remove(); // null이 아닐 때 삭제
						}
						commentList.append( createCommentElement(result.comments) );
					}
					// createCommentPagination
					if( true ){
						createCommentPagination( result.activePage, result.nextPages, result.prevPages );
					}
				},
				complete: function( com ) {
					console.log("[complete]", com);
				}
			}
		);
	}
}
// 댓글리스트 element 만들기
function createCommentElement( comments ){
	let elements = document.createElement("div");
	elements.setAttribute("class", "comment-container row");

	for(let i = 0; i < comments.length; i++){
		let container = document.createElement("div");
		container.setAttribute("class", "comment-item " + (i + 1));
		
		let row1 = document.createElement("div");
		let row2 = document.createElement("div");
		row1.setAttribute("class", "row comment-info");
		row2.setAttribute("class", "row comment-text");
		
		let r1c1 = document.createElement("div");
		let r1c2 = document.createElement("div");
		let r1c3 = document.createElement("div");
		
		let r2c1 = document.createElement("div");

		r1c1.setAttribute("class","col s4");
		r1c2.setAttribute("class","col s4");
		r1c3.setAttribute("class","col s4");
		
		r2c1.setAttribute("class","col s12");
		
		let no = document.createElement("a");
		let writer = document.createElement("a");
		let date = document.createElement("a");
		let comment = document.createElement("textarea");
		
		no.innerText = comments[i]["no"];
		writer.innerText = comments[i]["writer"];
		date.innerText = comments[i]["date"];
		comment.innerText = comments[i]["comment"];
		comment.setAttribute("class", "materialize-textarea");

		r1c1.append(no);
		r1c2.append(writer);
		r1c3.append(date);
		
		row1.append(r1c1);
		row1.append(r1c2);
		row1.append(r1c3);
		
		r2c1.append(comment);
		row2.append(r2c1);
		
		container.append(row1);
		container.append(row2);
	
		elements.append(container);
	}
	
	return elements;
}

// AJAX로 동정 생성할건데 어떻게 하면 좋을까?
// 이런식으로 계속 만들어 나가야하나?
/**
 * 좌우버튼이 있으면 다시 생성할 필요가 없으니까, 검사만하면 될듯함
 * 이렇게 계쏙 생성하기만 하기보단 검사하고 넘어가는게 더 비용적으로 저렴할듯
 */
function createCommentPagination(activePage, nextPages, prevPages){
	// TODO: 이전 페이지 버튼이 있는지 확인
	// TODO: 페이지 버튼들이 있는지 확인
	// TODO: 다음 페이지 버튼이 있는지 확인
	// TODO: 있다면 패스, 없다면 생성
	
	let pagination = document.getElementById("pagination");
	let li_prev_btn = pagination.getElementsByClassName("page-prev-btn");
	let li_next_btn = pagination.getElementsByClassName("page-next-btn");
	let li_nums_btn = pagination.getElementsByClassName("page-number-btn");
	let pagination_length = pagination.children.length;
	
	if( nextPages > 3 ){ nextPages = 3; }
	if( prevPages > 3 ){ prevPages = 3; }
	
	/**
	 * TODO: Page Numbers
	 * 1. Prev Numbers
	 * 2. Active Numbers
	 * 3. Next Numbers
	 * Active Page를 기준으로 Prev 최대 3개, Next 최대 3개를 보여줄 것
	 * 처음부터 다시 만드는게 좋을까 아니면... 원래 있던 elements를 수정하는게 나을까
	 * 
	 */
	// 리스트 삭제
	for(let i = 0 ; i < pagination_length; i++){
		pagination.children[0].remove();
	}
	
	// 1. Set Prev Numbers
	for(let prev = (activePage - prevPages); prev < activePage; prev++ ){
		pagination.appendChild(createNumbers( "prev", prev ));
	}
	// 2. Set Active Number
	pagination.appendChild( createNumbers("active", activePage));
	
	// 3. Set Next Numbers
	for(let next = (activePage + 1); next < (activePage + 1 + nextPages); next++ ){
		pagination.appendChild(createNumbers( "next", next ));
	}

	/**
	 * TODO: Create Previous Page Button
	 */
	if( activePage > 1 ){
		li_prev_btn = createNextAndPrevButton( "prev", activePage, li_prev_btn, pagination);
	}
	
	/**
	 * TODO: Create Next Page Button
	 */
	if( activePage < (activePage + nextPages) ){
		li_next_btn = createNextAndPrevButton( "next", activePage, li_next_btn, pagination );
	}
}
/**
 * 
 * @param type	String { "prev", "active", "next" }
 * @param page	Integer
 * @returns
 */
function createNumbers( type, page ){
	let li_num = document.createElement("li");
	let a_tag = document.createElement("a");
	
	li_num.setAttribute("class", "page-number-btn");
	
	a_tag.setAttribute("class", type);
	a_tag.setAttribute("href", "#");
	a_tag.setAttribute("onClick", "getComments("+INIT.id+","+page+")");
	a_tag.innerText = page;
	
	li_num.appendChild(a_tag);
	
	return li_num;
}
function createNextAndPrevButton(type, activePage, element, parent){
	let a_tag = null;
	let i_tag = null;
	
	// prev_btn이 없으면 생성
	if( element.length == 0){
		element = document.createElement("li");
		a_tag = document.createElement("a");
		i_tag = document.createElement("i");
		
		element.setAttribute("class", "page-"+type+"-btn");
		
		a_tag.appendChild(i_tag);
		element.appendChild(a_tag);
		
		if( type === "prev" ){
			parent.insertBefore( element, parent.firstChild );
		} else {
			parent.insertBefore( element, parent.lastChild.nextSibling );
		}
	} else {
		element = element[0];
		a_tag = element.children[0];
		i_tag = a_tag.children[0];
	}
	
	let movePage = ( type === "next" ? activePage + 1 : activePage - 1);
	
	a_tag.setAttribute("class", type);
	a_tag.setAttribute("href", "#");
	a_tag.setAttribute("onClick", "getComments( "+INIT.id+" , "+movePage+")");
	
	i_tag.setAttribute("class", "material-icons");
	i_tag.innerText = ( type === "next" ? "chevron_right" : "chevron_left" );
	
	return element;
}


// 댓글 작성하기 && after: 다시 목록불러오기

function getWritedComment(){
	// get DATA
	const commentWrite = document.getElementById("comment-write");
	
	let writer = commentWrite.getElementsByClassName("comment-input writer")[0];
	let password = commentWrite.getElementsByClassName("comment-input password")[0];
	let date = commentWrite.getElementsByClassName("comment-input date")[0];
	let comment = commentWrite.getElementsByClassName("comment-input comment")[0];
	
	let data = {
		"writer": writer.value,
		"password": password.value,
		"date": date.value,
		"comment": comment.value,
		"boardNo": INIT["id"] // 이걸 어디서 주고 받아올까
	}
	
	writer.value = "";
	password.value = "";
	comment.value = "";
	
	return data;
}

function saveComment(){
	const data = getWritedComment();
	$.ajax({
		contentType:"application/json",
		url: "http://localhost:8080/board3/board/comment/insert",
		type: "post",
		dataType: "json",
		data: JSON.stringify( data ),
		
		error: function(error){
			console.log("[ajax-error]", error);
		},
		success: function(result){
			console.log("[ajax-success]", result);
			
		},
		complete: function(){
			
			// 다시 호출하기
			console.log("[리스트 다시 불러오기]");
			if( commentList.childElementCount > 0 ){
				commentList.children[0].remove(); // null이 아닐 때 삭제
			}
			getComments( INIT.id, INIT.page );
		}
	});
}























