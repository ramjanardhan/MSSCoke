(function ($) {
    $.fn.extend({
        tablePaginate: function (options, recordPage) {

            //alert(recordPage)
            var defaults = {
                recordPerPage: recordPage, // Display records per page
                pageNumber: 1, // GoTo Pagenumber - Default : 1
                fullData: false, // True : Disable pagination, False - Enable Pagination
                buttonPosition: 'after', // before, after
                navigateType: 'navigator'		// navigator (first,prev,next,last buttons), full (display page numbers)

            };

            var options = $.extend(defaults, options);
            // alert(options+ "-options")
            var el = this;

            // GET total Records length
            var totalRecords = $(el).find('tbody').find('tr').length;
            // alert(totalRecords+ "-options")               
            if ($(el).find('tbody').find('tr').find('td')[0].colSpan >= '2')
            {
                $(".pagination").css('display', 'none');

                totalRecords = 0;
                //alert(totalRecords+ "-options")
            }

            // Pagination off
            if (defaults.fullData == 'true') {
                defaults.pageNumber = 1;
                defaults.recordPerPage = totalRecords;
            }

            // Identify Start & End values
            //alert(defaults.recordPerPage);
            var end = defaults.pageNumber * defaults.recordPerPage;
            var start = end - defaults.recordPerPage;
            start = start;
            //alert(start+"end"+end);				
            // Pagination button
            $('span.pagination').empty().remove();
            var buildButtons = "<span class='pagination' style='float:right;padding-top:10px'>";
            // Get Total Pages

            var totalPages = Math.ceil((totalRecords - 1) / defaults.recordPerPage);
            var pno = parseInt(defaults.pageNumber);

            //alert(totalPages)    



            if (defaults.navigateType == 'navigator' && totalRecords > 0 && totalPages !== 1) {


                var firstPage = 1;
                var nextPage = parseInt(defaults.pageNumber) + 1;
                var prevPage = parseInt(defaults.pageNumber) - 1;
                nextPage = (nextPage >= totalPages) ? totalPages : nextPage;
                prevPage = (prevPage < firstPage) ? firstPage : prevPage;
                //  buildButtons = '<span onclick="' + prevPage + '" class="pg-normal"> <font align="bottom" class="jumpbar"> Page: <img src="../includes/images/jumpbar_prev.gif" border="0"> </span> ';
                buildButtons += "<a href='javascript:void(0);' id='" + firstPage + "' class='btn btn-warning pagination-btn'>Page: <img src='../includes/images/jumpbar_prev.gif' border='0'></a>";
                // buildButtons += "<a href='javascript:void(0);' id='"+prevPage+"' class='btn btn-warning pagination-btn'><i class='fa fa-angle-left' style='color:#396E9D;'></i></a>";




                if (totalPages > 5) {
                    if ((totalPages - pno) == 3)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 2)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 1)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 3) + "' class='btn btn-warning pagination-btn'>" + (pno - 3) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 0)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 4) + "' class='btn btn-warning pagination-btn'>" + (pno - 4) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 3) + "' class='btn btn-warning pagination-btn'>" + (pno - 3) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                }
                if (totalPages == 5)
                {

                    if ((totalPages - pno) == 2)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 1)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 0)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 3) + "' class='btn btn-warning pagination-btn'>" + (pno - 3) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }

                }
                if (totalPages == 4)
                {


                    if ((totalPages - pno) == 2)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 1)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 0)
                    {
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 3) + "' class='btn btn-warning pagination-btn'>" + (pno - 3) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }

                }
                if (totalPages == 3)
                {
                    // alert(totalPages-pno);
                    if ((totalPages - pno) == 2)
                    {
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if ((totalPages - pno) == 1)
                    {
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }
                    if ((totalPages - pno) == 0)
                    {
                        // buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 2) + "' class='btn btn-warning pagination-btn'>" + (pno - 2) + "</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }

                }
                if (totalPages == 2)
                {
                    // alert(totalPages-pno);
                    if ((totalPages - pno) == 2)
                    {
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if ((totalPages - pno) == 1)
                    {

                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        //   buildButtons += "<a href='javascript:void(0);' id='"+(pno-1)+"' class='btn btn-warning pagination-btn'>"+(pno-1)+"</a>";
                    }
                    if ((totalPages - pno) == 0)
                    {
                        // buildButtons += "<a href='javascript:void(0);' id='"+(pno-3)+"' class='btn btn-warning pagination-btn'>"+(pno-3)+"</a>";
                        //  buildButtons += "<a href='javascript:void(0);' id='"+(pno-2)+"' class='btn btn-warning pagination-btn'>"+(pno-2)+"</a>";
                        buildButtons += "<a href='javascript:void(0);' id='" + (pno - 1) + "' class='btn btn-warning pagination-btn'>" + (pno - 1) + "</a>";
                    }

                }



                for (var i = pno; i <= pno + 4 && i <= totalPages; i++) {

                    buildButtons += "<a href='javascript:void(0);' id='" + i + "' class='btn btn-warning pagination-btn'>"
                    if (i == pno) {

                        buildButtons += "<u>" + i + "</u>";
                    }
                    else
                    {
                        buildButtons += i;
                    }
                    buildButtons += "</a>";
                }
                // alert("hi"+buildButtons)
                // buildButtons += '<span onclick="' + nextPage + '" class="pg-normal"> <img src="../includes/images/jumpbar_next.gif" border="0"></span></font>';
                buildButtons += "<a href='javascript:void(0);' id='" + nextPage + "' class='btn btn-warning pagination-btn'><img src='../includes/images/jumpbar_next.gif' border='0'/></a>";
                //buildButtons += "<a href='javascript:void(0);' id='" + totalPages + "' class='btn btn-warning pagination-btn'><i class='fa fa-angle-double-right' style='color:#396E9D;'></i></a>";

            } else {
                // Display page numbers
                if (totalPages !== 1)
                {
                    for (var i = 1; i <= totalPages; i++) {

                        buildButtons += "<button type='button' id='" + i + "' class='btn btn-warning pagination-btn'>" + i + "</button>";

                    }
                }
            }
            buildButtons += "</span>";

            if ($('.table-wrapper').length > 0)
            {
                (defaults.buttonPosition == 'after') ? $('.table-wrapper').after(buildButtons) : $('.table-wrapper').append(buildButtons);
            }
            else
            {
                (defaults.buttonPosition == 'after') ? $(this).after(buildButtons) : $(this).append(buildButtons);
            }
            //			alert('hi'+$('.table-wrapper').length);
            //(defaults.buttonPosition == 'after') ? $(this).after(buildButtons) : $(this).append(buildButtons);
            // Display records based on pagination settings

            $(el).find('tbody').find('tr').each(function (rowIndex, data) {

                if (rowIndex != 0) {
                    $(this).hide();
                }

                if ((start + 1) <= rowIndex && (end + 1) > rowIndex) {
                    $(this).show();
                }
            });

            // Pagination button live click
            $(".pagination-btn").on("click", function () {

                //                                         $(".pagination").insertAfter("table");
                var id = $(this).attr("id");
                //   $( ".pagination" ).insertAfter( "table" );                                   

                $(el).tablePaginate({
                    pageNumber: id,
                    recordPerPage: defaults.recordPerPage,
                    fullData: defaults.fullData,
                    buttonPosition: defaults.buttonPosition,
                    navigateType: defaults.navigateType
                });

            });

        }
    });
})(jQuery);