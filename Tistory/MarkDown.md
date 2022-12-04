[URL]
# https://cdnjs.com/libraries/github-markdown-css

스킨편집 - HTML 편집 - CSS 편집

[HTML] </head> 바로 위쪽에 써주자

<!-- github Markdown -->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.1.0/github-markdown-dark.min.css" integrity="sha512-USRvpT7dJFA7mrRx4+esmy+2mYr8vlgmDLFpkNeVEd+D5CgQvULKPYVnDV97Ywfek+e//HdSA0NlaPe4oqkwfQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- github Markdown -->

[CSS]
/* github Markdown */

.markdown-body { 
	box-sizing: border-box; 
	min-width: 200px; 
	max-width: 980px; 
	margin: 0 auto; 
	padding: 45px; 
} 


@media (max-width: 767px) { 
	.markdown-body { 
		padding: 15px;
	} 
}

/* github Markdown */


[글 작성]
<div class="markdown-body">

</div>



===

https://inpa.tistory.com/entry/MarkDown-%F0%9F%93%9A-%EB%A7%88%ED%81%AC%EB%8B%A4%EC%9A%B4-%EB%AC%B8%EB%B2%95-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC
[Headers 헤더]
#으로 시작하는 텍스트.
#은 하나부터 여섯개까지 가능.
#이 늘어날때마다 제목의 스케일 낮아집니다.
H1은 ===로도 만들 수 있습니다.
H2는 ---로도 만들 수 있습니다.


[Horizontal Rules 수평선]
- 또는 * 또는 _ 을 3개 이상 작성.
  단, -을 사용할 경우 header로 인식할 수 있으니 이 전 라인은 비워두어야 합니다.


[Line Breaks 줄바꿈]
<br>를 활용해서 줄바꿈을 할 수 있습니다.
엔터로 칸을 띄면 다음 행으로 넘어가게 됩니다. <br>은 하나의 문장에서 줄바꿈


[Emphasis 강조]
기울여 쓰기(italic) : * 또는 _로 감싼 텍스트.
두껍게 쓰기(bold) : ** 또는 __로 감싼 텍스트.
취소선 : ~~로 감싼 텍스트.

이탤릭체와 두껍게를 같이 사용할 수 있습니다.
_This will also be italic_

**This will also be bold**

~~This is canceled~~

_You **can** ~~combine~~ them_


[Blockquotes 인용]

>으로 시작하는 텍스트.
>는 3개까지 가능합니다.


참고로 인용구 안에는 제목이나 리스트, 텍스트박스 등 도 넣을 수 있습니다.
> # this is h1!
> * list
> textbox`


[Unordered lists 순서가 없는 목록]
*, +, - 를 이용해서 순서가 없는 목록을 만들 수 있습니다.
들여쓰기를 하면 모양이 바뀝니다.

[Ordered lists 순서가 있는 목록]
숫자를 기입하면 순서가 있는 목록이 됩니다.
들여쓰기를 하면 모양이 바뀝니다.
숫자를 무엇을 쓰느냐는 그다지 큰 의미가 없고 순서대로 알아서 숫자를 매깁니다


[Backslash Escapes]
특수문자를 표현할 때, 표시될 문자 앞에 \를 넣고 특수문자를 쓰면 됩니다.
* 특수문자 출력안됨
- 특수문자 출력안됨
\* 특수문자 출력
\- 특수문자 출력


[이미지]
링크와 비슷하지만 앞에 !가 붙습니다.
인라인 이미지 ![alt text](/test.png)
링크 이미지 ![alt text](image_URL)
이미지의 사이즈를 변경하기 위해서는 <img width="OOOpx" height="OOOpx"></img>와 같이 표현합니다.
![텍스트](이미지파일경로.jpg)
![텍스트](이미지파일URL)


이미지 파일에 마우스를 올렸을 때 커서 옆에 나오는 텍스트 설정

![텍스트](이미지파일경로.jpg "이미지이름")
![텍스트](이미지파일URL "이미지이름")


링크와 이미지를 합친 문법 (이미지를 링크로 사용)

[ ![텍스트](이미지URL) ]( 링크URL )

[![텍스트](https://t1.daumcdn.net/cfile/tistory/2444873B57E257821F)](https://unity3d.com/kr)