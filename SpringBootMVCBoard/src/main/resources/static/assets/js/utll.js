function makeDateStr(year, month, day, type) {
    return year + type + (month < 10 ? '0' + month : month) + type + (day < 10 ? '0' + day : day);
}

function makeTimeStr(hour, minute, second, type) {
    return hour + type + (minute < 10 ? '0' + minute : minute) + type + (second < 10 ? '0' + second : second);
}

function makePaginationHtml(listRowCount, pageLinkCount, currentPageIndex, totalListCount, htmlTargetId) {
    const targetUI = document.querySelector(`#${htmlTargetId}`);

    const pageCount = Math.ceil(totalListCount / listRowCount);

    let startPageIndex = (currentPageIndex % pageLinkCount === 0)
        ? ((currentPageIndex / pageLinkCount) - 1) * pageLinkCount + 1
        : Math.floor(currentPageIndex / pageLinkCount) * pageLinkCount + 1;

    let endPageIndex = startPageIndex + pageLinkCount - 1;
    if (endPageIndex > pageCount) endPageIndex = pageCount;

    const prev = startPageIndex > 1;
    const next = endPageIndex < pageCount;

    let paginationHTML = '<ul class="pagination justify-content-center">';

    if (prev) {
        paginationHTML += `
            <li class="page-item">
                <a class="page-link" href="javascript:movePage(${startPageIndex - 1})" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>`;
    }

    for (let i = startPageIndex; i <= endPageIndex; i++) {
        if (i === currentPageIndex) {
            paginationHTML += `<li class="page-item active"><a class="page-link" href="javascript:movePage(${i})">${i}</a></li>`;
        } else {
            paginationHTML += `<li class="page-item"><a class="page-link" href="javascript:movePage(${i})">${i}</a></li>`;
        }
    }

    if (next) {
        paginationHTML += `
            <li class="page-item">
                <a class="page-link" href="javascript:movePage(${endPageIndex + 1})" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>`;
    }

    paginationHTML += '</ul>';
    targetUI.innerHTML = paginationHTML;
}
