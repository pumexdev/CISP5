/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function createDataTable(array,tableid)
{
    var html = "";
    for(var t=0;t<array.length;++t)
    {
        html+="<tr>";
        var singlearray = array[t];
        for(var v=0;v<singlearray.length;++v)
        {
            html+="<td>"+singlearray[v];
            html+="</td>";
        }
        html+="</tr>";
    }
    
    $("#"+tableid).html(html);
}

function getImgData(chartContainer)
{
    var chartArea = document.getElementById(chartContainer).children[0];
    var svg = chartArea.innerHTML.substring(chartArea.innerHTML.indexOf("<svg"), chartArea.innerHTML.indexOf("</svg>") + 6); 
    var doc = document.getElementById(chartContainer).ownerDocument;
    var canvas = doc.createElement('canvas');
    canvas.setAttribute('width', chartArea.offsetWidth);
    canvas.setAttribute('height', chartArea.offsetHeight);
        
    canvas.setAttribute
    (
        'style',
        'position: absolute; ' +
        'top: ' + (-chartArea.offsetHeight * 2) + 'px;' +
        'left: ' + (-chartArea.offsetWidth * 2) + 'px;'
    );
    doc.body.appendChild(canvas);
    canvg(canvas, svg);
    var imgData = canvas.toDataURL("image/png");
    canvas.parentNode.removeChild(canvas);
    return imgData;
}
      
function saveAsImg(chartContainer)
{
    var imgData = getImgData(chartContainer);
        
    // Replacing the mime-type will force the browser to trigger a download
    // rather than displaying the image in the browser window.
    //window.location = imgData.replace("image/png", "image/png");
    window.open(imgData.replace("image/png", "image/png"), 'newWindow', 'height=auto,width=auto,window');
}
      
function toImg(chartContainer, imgContainer)
{ 
    var doc = chartContainer.ownerDocument;
    var img = doc.createElement('img');
    img.src = getImgData(chartContainer);
    while (imgContainer.firstChild)
    {
        imgContainer.removeChild(imgContainer.firstChild);
    }
    imgContainer.appendChild(img);
}