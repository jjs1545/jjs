/*
totalCount = 총 게시물 구하는 쿼리
select count(*) as totalCount
from board(게시물이 있는 테이블)
*/

/*
1. 한 페이지에 출력될 게시물 수(countList)
2. 한 화면에 출력될 페이지 수(countPage)
3. 현재 페이지 번호(이하 page)
*/

/*
처음 해야할 것은 총 몇 페이지가 존재하는지 알아내는 것입니다(총 페이지, 이하 totalPage). 
이 때 필요한 것은 countList 인데, 이해를 돕기 위해 countList = 10 혹은 countList = 5 라고 가정하고 계산해보겠습니다.
25 / countList
25 / 10 = 2 나머지 5
25 / 5 = 5 나머지 0
우리가 기대하는 수는? 바로 3 와 5 입니다. 
Java 와 같은 언어에서는 int 형으로 보통 계산을 하기 때문에 25 /10 을 해버리면 2 만 나오게 됩니다. 
그렇다고 무조건 1 을 더하게 되면 아래의 countList = 5 와 같은 경우 때문에 정확한 총 페이지 수(totalPage)을 계산할 때 무조건 1 을 더해선 안됩니다(의외로 이렇게 계산해서 마지막 페이지에 게시물 안나오는 곳이 많더군요). 
위에서 보듯이 가장 정확하면서 단순한 방법은 나머지가 있을 경우에만 1 을 더해줘야 합니다. Java 로 된 최종 코드는 아래와 같이 되겠죠.
*/


//예를 들면 totalCount = maNtService.getIntCommonService(inMap, "app.getSearchCount");
int totalCount = 25; //  총페이지 쿼리로 구한다. 예를들어 25개
int countList = 10;  // 한 페이지에 출력될 게시물 수
int totalPage = totalCount / countList; //총 페이지 수 

if(totalCount % countList>0) {
	totalpage++;
}

/*
OR
*/

int totalCount = 25;
int countList = 10;
int totalPage = totalCount/countList;

if(totalCount > countList * totalPage) {
	totalPage++;
}

/*
이렇게 하면 총 페이지 수를 알 수 있습니다. 이 값을 통해서 한 화면에 출력될 페이지 수가 10 개라고 해도, 3 페이지까지만 존재할 경우 1 에서 3 페이지까지만 하단에 출력해줄 수 있습니다(이런 처리 안해서 없는 페이지를 출력하는 곳도 많더군요).
또한, 잘못된 현재 페이지에 대한 보정도 가능합니다.
현재 페이지 번호가 총 페이지 번호보다 크다면 어떻게 해야 할까요? 현재 페이지를 강제로 총 페이지 번호로 치환하는 것도 방법이 될 것입니다.
*/

int page = 2; // 현재 페이지
int totalCount = 25;
int countList = 10;
int totalPage = totalCount/countList;

if (totalCount % countList>0) {
	totalPage++;
}

if (totalPage < page) {
	page = totalPage;
}

/*
이번에는, 하단에 표시될 페이지 번호들을 어떻게 알아낼 수 있는지 알아보겠습니다. 
이번 예제에는 totalCount = 255, countList = 10, countPage = 10 으로 하겠습니다. 
그리고 현재 페이지인 page 는 5 와 22 의 두 가지 경우를 생각해보겠습니다. 
또한, 페이지 표시 방식은 대한민국에서 가장 많이 쓰는 방식인 10 개의 페이지를 보이는 방식으로 하겠습니다.
이 방식은 현재 페이지 기준으로 앞 뒤 몇 개의 페이지를 보여주는 외국의 방식보다 대한민국에서 더 많이 쓰는 방식입니다.
먼저 page = 5 일 경우입니다. 
이 경우 우리가 예상하는 결과는 하단에 1 ~ 10 까지의 페이지 번호가 표시되는 것입니다(countPage = 10 이니까요).
이런 페이지 번호 계산을 하는 방법은 몇 가지가 있습니다만, 
가장 간단하고 쉬운 방법은 바로 시작 페이지 번호를 계산해내는 것입니다. 
page = 5 일 경우 시작 페이지(startPage)는 1, 마지막 페이지(endPage)는 시작 페이지에서 10 페이지(countPage 가 10 이니까요)까지라는 건 금방 이해하시리라 생각합니다. 
그럼 page = 5 에서 어떻게 1 페이지를 찾아낼 수 있을까요? 아주 쉽습니다. 
그냥 countPage 로 나눠버리면 됩니다(그리고 1 을 더해줘야 해요!). 
그럼 마지막 페이지는요? countPage 을 더하면 됩니다. 
단, 더한 뒤 1 을 빼주는 작업은 잊으시면 안됩니다. 왜 그런지는 생각해보시면 금방 깨닫게 되실꺼구요.
*/

int page = 5;
int countPage = 10;
int startPage = ((page-1)/10) * 10 + 1;
int endPage = startPage + countPage - 1;

/*
실제 보이는 페이지는 1 ~ 10 까지이지만, 실제 페이지 번호는 0 ~9 까지로 처리하는 경우도 있습니다.
그게 Java 와 같은 언어에선 처리가 더 간단하기 때문인데요...
저 역시 시작 페이지는 0 으로 해서 처리한 클래스를 만들어서 씁니다만, 
이해를 돕기 위해 여기서는 1 페이지는 1 의 page 번호를 가지도록 하겠습니다.
어쨌든, 저렇게 계산을 하면 startPage = 1, endPage = 10 이라는 결과가 나옵니다.
그럼 화면에 출력할 때에는요?
*/

int page = 5;
int countPage = 10;
int startPage = ((page-1)/10) * 10 + 1;
int endPage = startPage + countPage - 1;

for (int iCount = startPage; iCount <= endPage; iCount++) {
	System.out.print(" " + iCount +_ " ");
}

/*
이런 식으로 출력하면 페이지 번호가 연달아서 출력이 되겠죠.
그렇다면 page 가 22 인 경우에는요?
먼저 단순히 산수 계산을 해보면 255 개의 게시물이 있을 경우 총 26 페이지가 존재할 것이고, 
22 페이지가 있는 곳에는 21 에서 30 페이지 영역일 것입니다. 
하지만, 26 페이지까지이기 때문에 단순히 21 페이지에서 countPage 을 더하면 안된다는 것을 대번에 눈치 채셨을 겁니다. 
그래서 이 경우에도 마지막 페이지는 총 페이지 수로 대체를 해줘야 합니다.
*/

int page = 22;
int countList = 10;
int countPage = 10;
int totalCount = 255;
int totalPage = totalCount/countList;

if(totalCount % countList>0) {
	totalPage++;
}

if (totalPage < page) {
	page = totalPage;
}

int startPage = ((page-1)/10) * 10 + 1;
int endPage = startPage + countPage - 1;

// 여기서 마지막 페이지를 보정해줍니다.
if (endPage > totalPage) {
	endPage = totalPage;
}

// [paging]
// 이 부분은 아래에서 추가로 설명합니다.

for (int iCount = startPage; iCount <= endPage; iCount++) {
	System.out.print(" " + iCount + " ");
}

/*
대충 끝이 보이네요. 
위에서 [paging] 이라고 표시된 부분에서 페이지 번호를 출력하는데, 
출력을 할 때 css 코드를 넣거나 <a> 태그를 이용해서 연결을 하면 페이지 이동이 가능합니다. 
이 때 현재 페이지는 굵은 글씨체로 표시하고 <a> 태그를 빼기 위해 아래와 같이 처리도 가능하겠죠.
*/

// [paging]
// 이렇게 개선됩니다.

for (int iCount = startPage; iCount <= endPage; iCount++) {
	if (iCount == page) {
		System.out.print(" <b>" + iCount + "</b>");
	} else {
		System.out.print(" " + iCount + " ");
	}
}

/*
보통 첫페이지 이동이나 이전 페이지, 다음페이지, 끝페이지 이동 버튼도 추가로 달아줍니다. 
그래야 해당 페이지 리스트에 없는 곳으로도 이동이 될테니까요.
첫 페이지는 현재 페이지가 1 페이지가 아닐 때 표시되게 하는 경우도 있고, 
시작 페이지가 1페이지가 아닐 때 표시하는 경우도 있습니다. 
취향 문제죠. 어쨌든 이동하는 페이지는 항상 page = 1 이 되겠죠. 
이전 페이지도 마찬가지입니다. 1 페이지가 아닐 경우 현재 페이지보다 1 페이지 앞으로 이동하도록 page - 1 값을 가지고 이동하게 하면 되죠. 
다음페이지는 totalPage 와 비교해서 마찬가지로 표시해주면 됩니다.
*/

int page = 22;
int countList = 10;
int countPage = 10;
int totalCount = 255;
int totalPage = totalCount/countList;

if (totalCount % countList > 0) {
	totalPage++;
}

if (totalPage < page) {
	page = totalPage;
}

int startPage = ((page-1)/10)*10 + 1;
int endPage = startPage + countPage - 1;

if (endPage > totalPage) {
	endPage = totalPage;
}

if (startPage > 1) {
	System.out.print("<a href=\"?page=1\">처음</a>");
}

if (page > 1) {
	System.out.println("<a href=\"?page=" + (page -1) + "\">이전</a>");
}

for (int iCount = startPage; iCount <= endPage; iCount++) {
	if (iCount == page) {
		System.out.print("<b>" + iCount + "</b>");
	}else {
		System.out.print(" " + iCount + " ");
	}
}

if (page < totalPage) {
	 System.out.println("<a href=\"?page=" + (page + 1)  + "\">다음</a>");

}

if (endPage < totalPage) {
    System.out.print("<a href=\"?page=" + totalPage + "\">끝</a>");
}