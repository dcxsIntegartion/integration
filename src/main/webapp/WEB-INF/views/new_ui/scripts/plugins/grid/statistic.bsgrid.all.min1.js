/**
 * jQuery.bsgrid v1.35 by
 * 
 * @Baishui2004 Copyright 2014 Apache v2 License
 *              https://github.com/baishui2004/jquery.bsgrid
 */

String.prototype.startWith = function(a) {
	if (a == null || a == "" || this.length == 0 || a.length > this.length) {
		return false
	} else {
		return this.substr(0, a.length) == a
	}
};
String.prototype.endWith = function(a) {
	if (a == null || a == "" || this.length == 0 || a.length > this.length) {
		return false
	} else {
		return this.substring(this.length - a.length) == a
	}
};
String.prototype.replaceAll = function(a, b) {
	return this.replace(new RegExp(a, "gm"), b)
};
function StringBuilder() {
	if (arguments.length) {
		this.append.apply(this, arguments)
	}
}
StringBuilder.prototype = function() {
	var c = Array.prototype.join, d = Array.prototype.slice, a = /\{(\d+)\}/g, b = function() {
		return c.call(this, "")
	};
	return {
		constructor : StringBuilder,
		length : 0,
		append : Array.prototype.push,
		appendFormat : function(e) {
			var g = 0, f = d.call(arguments, 1);
			this.append(a.test(e) ? e.replace(a, function(h, j) {
				return f[j]
			}) : e.replace(/\?/g, function() {
				return f[g++]
			}));
			return this
		},
		size : function() {
			return this.toString().length
		},
		toString : b,
		valueOf : b
	}
}();
$.bsgrid = {
	param : function(c, b) {
		if (b == undefined) {
			b = false
		}
		if (!b) {
			return $.param(c)
		}
		var d = new StringBuilder();
		if (c instanceof Array) {
			$.each(c, function(f, e) {
				d.append("&" + e.name + "=");
				d.append(encodeURIComponent(encodeURIComponent(e.value)))
			})
		} else {
			for ( var a in c) {
				d.append("&" + a + "=");
				d.append(encodeURIComponent(encodeURIComponent(c[a])))
			}
		}
		return d.length > 0 ? d.toString().substring(1) : ""
	},
	getKeysString : function(c, a) {
		if (a == undefined) {
			a = ","
		}
		var d = new StringBuilder();
		if (c instanceof Array) {
			$.each(c, function(f, e) {
				if ((d.toString() + a).indexOf(a + e.name + a) == -1) {
					d.append(a + e.name)
				}
			})
		} else {
			for ( var b in c) {
				d.append(a + b)
			}
		}
		return d.length > 0 ? d.toString().substring(1) : ""
	},
	forcePushPropertyInObject : function(b, a, c) {
		if (b.hasOwnProperty(a)) {
			b[a + "_f"] = c
		} else {
			b[a] = c
		}
	},
	adaptAttrOrProp : function(b, d, c) {
		var a = parseInt($.fn.jquery.substring(0, $.fn.jquery.indexOf(".", 2))
				.replace(".", ""));
		if (c == undefined) {
			if (a >= 16) {
				return b.prop(d)
			} else {
				return b.attr(d)
			}
		} else {
			if (a >= 16) {
				b.prop(d, c)
			} else {
				b.attr(d, c)
			}
		}
	}
};
(function(a) {
	a.fn.bsgrid_paging = {
		defaults : {
			loopback : false,
			pageSize : 20,
			pageSizeSelect : false,
			pageSizeForGrid : [ 5, 10, 20, 25, 50, 100, 200, 500 ],
			pageIncorrectTurnAlert : true,
			pagingLittleToolbar : false,
			pagingBtnClass : "pagingBtn"
		},
		pagingObjs : {},
		init : function(h, g) {
			var d = {
				settings : a.extend(true, {}, a.fn.bsgrid_paging.defaults, g),
				pagingId : h,
				totalRowsId : h + "_totalRows",
				totalPagesId : h + "_totalPages",
				curPageId : h + "_curPage",
				gotoPageInputId : h + "_gotoPageInput",
				gotoPageId : h + "_gotoPage",
				refreshPageId : h + "_refreshPage",
				pageSizeId : h + "_pageSize",
				firstPageId : h + "_firstPage",
				prevPageId : h + "_prevPage",
				nextPageId : h + "_nextPage",
				lastPageId : h + "_lastPage",
				startRowId : h + "_startRow",
				endRowId : h + "_endRow",
				totalRows : 0,
				totalPages : 0,
				curPage : 1,
				curPageRowsNum : 0,
				startRow : 0,
				endRow : 0
			};
			if (g.pageSizeForGrid != undefined) {
				d.settings.pageSizeForGrid = g.pageSizeForGrid
			}
			var b = {
				options : d,
				page : function(i) {
					a.fn.bsgrid_paging.page(i, d)
				},
				getCurPage : function() {
					return a.fn.bsgrid_paging.getCurPage(d)
				},
				refreshPage : function() {
					a.fn.bsgrid_paging.refreshPage(d)
				},
				firstPage : function() {
					a.fn.bsgrid_paging.firstPage(d)
				},
				prevPage : function() {
					a.fn.bsgrid_paging.prevPage(d)
				},
				nextPage : function() {
					a.fn.bsgrid_paging.nextPage(d)
				},
				lastPage : function() {
					a.fn.bsgrid_paging.lastPage(d)
				},
				gotoPage : function(i) {
					a.fn.bsgrid_paging.gotoPage(d, i)
				},
				createPagingToolbar : function() {
					return a.fn.bsgrid_paging.createPagingToolbar(d)
				},
				setPagingToolbarEvents : function() {
					a.fn.bsgrid_paging.setPagingToolbarEvents(d)
				},
				dynamicChangePagingButtonStyle : function() {
					a.fn.bsgrid_paging.dynamicChangePagingButtonStyle(d)
				},
				setPagingValues : function(j, i) {
					a.fn.bsgrid_paging.setPagingValues(j, i, d)
				}
			};
			a.fn.bsgrid_paging.pagingObjs[h] = b;
			a("#" + h).append(b.createPagingToolbar());
			if (d.settings.pageSizeSelect) {
				if (a.inArray(d.settings.pageSize, d.settings.pageSizeForGrid) == -1) {
					d.settings.pageSizeForGrid.push(d.settings.pageSize)
				}
				d.settings.pageSizeForGrid.sort(function(j, i) {
					return j - i
				});
				var f = new StringBuilder();
				for (var e = 0; e < d.settings.pageSizeForGrid.length; e++) {
					var c = d.settings.pageSizeForGrid[e];
					f.append('<option value="' + c + '">' + c + "</option>")
				}
				a("#" + d.pageSizeId).html(f.toString()).val(
						d.settings.pageSize)
			}
			b.setPagingToolbarEvents();
			return b
		},
		getPagingObj : function(c) {
			var b = a.fn.bsgrid_paging.pagingObjs[c];
			return b ? b : null
		},
		page : function(c, b) {
			var d = a.fn.bsgrid.getGridObj(b.settings.gridId);
			d.options.settings.pageSize = b.settings.pageSize;
			a.fn.bsgrid.page(c, d.options)
		},
		getCurPage : function(b) {
			var c = a("#" + b.curPageId).html();
			return c == "" ? 1 : c
		},
		refreshPage : function(b) {
			a.fn.bsgrid_paging.page(a.fn.bsgrid_paging.getCurPage(b), b)
		},
		firstPage : function(b) {
			var c = a.fn.bsgrid_paging.getCurPage(b);
			if (c <= 1) {
//				a.fn.bsgrid_paging.incorrectTurnAlert(b,
//						a.bsgridLanguage.isFirstPage);
				return
			}
			a.fn.bsgrid_paging.page(1, b)
		},
		prevPage : function(b) {
			var c = a.fn.bsgrid_paging.getCurPage(b);
			if (c <= 1) {
				if (b.settings.loopback && b.totalPages > 0) {
					a.fn.bsgrid_paging.page(b.totalPages, b);
					return
				} else {
//					a.fn.bsgrid_paging.incorrectTurnAlert(b,
//							a.bsgridLanguage.isFirstPage);
					return
				}
			}
			a.fn.bsgrid_paging.page(parseInt(c) - 1, b)
		},
		nextPage : function(b) {
			var c = a.fn.bsgrid_paging.getCurPage(b);
			if (c >= b.totalPages) {
				if (b.settings.loopback && c > 0) {
					a.fn.bsgrid_paging.page(1, b);
					return
				} else {
//					a.fn.bsgrid_paging.incorrectTurnAlert(b,
//							a.bsgridLanguage.isLastPage);
					return
				}
			}
			a.fn.bsgrid_paging.page(parseInt(c) + 1, b)
		},
		lastPage : function(b) {
			var c = a.fn.bsgrid_paging.getCurPage(b);
			if (c >= b.totalPages) {
//				a.fn.bsgrid_paging.incorrectTurnAlert(b,
//						a.bsgridLanguage.isLastPage);
				return
			}
			a.fn.bsgrid_paging.page(b.totalPages, b)
		},
		gotoPage : function(b, c) {
			if (c == undefined) {
				c = a("#" + b.gotoPageInputId).val()
			}
			if (a.trim(c) == "" || isNaN(c)) {
				alert(a.bsgridLanguage.needInteger)
			} else {
				if (parseInt(c) < 1) {
					/* 如果输入的数字比1小 则直接跳转到 第一页 */
					a("#" + b.gotoPageInputId).val(1);
					a.fn.bsgrid_paging.page(parseInt(1), b)
				}else if (parseInt(c) > b.totalPages){
					/* 如果输入的数字比最大页码数大 则直接跳转到 末页 */
					a("#" + b.gotoPageInputId).val(b.totalPages);
					a.fn.bsgrid_paging.page(parseInt( b.totalPages), b)
					
				}else {
					a("#" + b.gotoPageInputId).val(c);
					a.fn.bsgrid_paging.page(parseInt(c), b)
				}
			}
		},
		incorrectTurnAlert : function(b, c) {
			if (b.settings.pageIncorrectTurnAlert) {
				alert(c)
			}
		},
		createPagingToolbar : function(b) {
			var e = new StringBuilder();
			var c = b.settings.pagingLittleToolbar;
			e.append('<table class="bsgridPaging'
					+ (c ? " pagingLittleToolbar" : "")
					+ (b.settings.pageSizeSelect ? "" : " noPageSizeSelect")
					+ '">');
			e.append("<tr>");
//			if (b.settings.pageSizeSelect) {
//				e.append("<td>"
//						+ a.bsgridLanguage.pagingToolbar.pageSizeDisplay(
//								b.pageSizeId, c) + "</td>")
//			}
//			e.append("<td>"
//					+ a.bsgridLanguage.pagingToolbar.currentDisplayRows(
//							b.startRowId, b.endRowId, c) + "</td>");
			e.append("<td>"
					+ a.bsgridLanguage.pagingToolbar.totalRows(b.totalRowsId)
					+ "</td>");
			var d = b.settings.pagingBtnClass;
			e.append("<td>");
			e.append('<input class="' + d + ' firstPage" type="button" id="'
					+ b.firstPageId + '" value="'
					+ (c ? "" : a.bsgridLanguage.pagingToolbar.firstPage)
					+ '" title="首页" />');
			e.append("&nbsp;");
			e.append('<input class="' + d + ' prevPage" type="button" id="'
					+ b.prevPageId + '" value="'
					+ (c ? "" : a.bsgridLanguage.pagingToolbar.prevPage)
					+ '" title="上一页" />');
			e.append("</td>");
			e.append("<td>"
					+ a.bsgridLanguage.pagingToolbar
							.currentDisplayPageAndTotalPages(b.curPageId,
									b.totalPagesId) + "</td>");
			e.append("<td>");
			e.append('<input class="' + d + ' nextPage" type="button" id="'
					+ b.nextPageId + '" value="'
					+ (c ? "" : a.bsgridLanguage.pagingToolbar.nextPage)
					+ '"  title="下一页" />');
			e.append("&nbsp;");
			e.append('<input class="' + d + ' lastPage" type="button" id="'
					+ b.lastPageId + '" value="'
					+ (c ? "" : a.bsgridLanguage.pagingToolbar.lastPage)
					+ '"  title="末页" />');
			e.append("</td>");
			e.append('<td class="gotoPageInputTd">');
			e.append('<input class="gotoPageInput" type="text" id="'
					+ b.gotoPageInputId + '" />');
			e.append("</td>");
			e.append('<td class="gotoPageButtonTd">');
			e.append('<input class="' + d + ' gotoPage" type="button" id="'
					+ b.gotoPageId + '" value="'
					+ (c ? "" : a.bsgridLanguage.pagingToolbar.gotoPage)
					+ '"   title="跳转" />');
			e.append("</td>");
			e.append('<td class="refreshPageTd">');
			e.append('<input class="' + d + ' refreshPage" type="button" id="'
					+ b.refreshPageId + '" value="'
					+ (c ? "" : a.bsgridLanguage.pagingToolbar.refreshPage)
					+ '"  title="刷新" />');
			e.append("</td>");
			e.append("</tr>");
			e.append("</table>");
			return e.toString()
		},
		setPagingToolbarEvents : function(b) {
			if (b.settings.pageSizeSelect) {
				a("#" + b.pageSizeId).change(function() {
					b.settings.pageSize = parseInt(a(this).val());
					a(this).trigger("blur");
					a.fn.bsgrid_paging.page(1, b)
				})
			}
			a("#" + b.firstPageId).click(function() {
				a.fn.bsgrid_paging.firstPage(b)
			});
			a("#" + b.prevPageId).click(function() {
				a.fn.bsgrid_paging.prevPage(b)
			});
			a("#" + b.nextPageId).click(function() {
				a.fn.bsgrid_paging.nextPage(b)
			});
			a("#" + b.lastPageId).click(function() {
				a.fn.bsgrid_paging.lastPage(b)
			});
			a("#" + b.gotoPageInputId).keyup(function(c) {
				if (c.which == 13) {
					a.fn.bsgrid_paging.gotoPage(b)
				}
			});
			a("#" + b.gotoPageId).click(function() {
				a.fn.bsgrid_paging.gotoPage(b)
			});
			a("#" + b.refreshPageId).click(function() {
				a.fn.bsgrid_paging.refreshPage(b)
			})
		},
		dynamicChangePagingButtonStyle : function(b) {
			var c = "disabledCls";
			if (b.curPage <= 1) {
				a("#" + b.firstPageId).addClass(c);
				a("#" + b.prevPageId).addClass(c)
			} else {
				a("#" + b.firstPageId).removeClass(c);
				a("#" + b.prevPageId).removeClass(c)
			}
			if (b.curPage >= b.totalPages) {
				a("#" + b.nextPageId).addClass(c);
				a("#" + b.lastPageId).addClass(c)
			} else {
				a("#" + b.nextPageId).removeClass(c);
				a("#" + b.lastPageId).removeClass(c)
			}
		},
		setPagingValues : function(i, g, f) {
			i = Math.max(i, 1);
			var b = f.settings.pageSize;
			var h = parseInt(g / b);
			h = parseInt((g % b == 0) ? h : h + 1);
			var e = (i * b < g) ? b : (g - (i - 1) * b);
			var d = (i - 1) * b + 1;
			var c = d + e - 1;
			d = e <= 0 ? 0 : d;
			c = e <= 0 ? 0 : c;
			f.totalRows = g;
			f.totalPages = h;
			f.curPage = i;
			f.curPageRowsNum = e;
			f.startRow = d;
			f.endRow = c;
			a("#" + f.totalRowsId).html(f.totalRows);
			a("#" + f.totalPagesId).html(f.totalPages);
			a("#" + f.curPageId).html(f.curPage);
			a("#" + f.startRowId).html(f.startRow);
			a("#" + f.endRowId).html(f.endRow);
			a.fn.bsgrid_paging.dynamicChangePagingButtonStyle(f)
		}
	}
})(jQuery);
(function($) {
	$.fn.bsgrid = {
		defaults : {
			dataType : "json",
			localData : false,
			url : "",
			otherParames : false,
			autoLoad : true,
			pageAll : false,
			pageSize : 20,
			pageSizeSelect : false,
			pageSizeForGrid : [ 5, 10, 20, 25, 50, 100, 200, 500 ],
			pageIncorrectTurnAlert : true,
			multiSort : false,
			displayBlankRows : true,
			lineWrap : false,
			stripeRows : false,
			changeColorIfRowSelected : true,
			pagingLittleToolbar : true,//默认改成true 使用简单图标
			pagingToolbarAlign : "right",
			pagingBtnClass : "pagingBtn",
			displayPagingToolbarOnlyMultiPages : false,
			isProcessLockScreen : true,
			longLengthAotoSubAndTip : true,
			colsProperties : {
				align : "center",
				maxLength : 40,
				indexAttr : "w_index",
				sortAttr : "w_sort",
				alignAttr : "w_align",
				lengthAttr : "w_length",
				renderAttr : "w_render",
				hiddenAttr : "w_hidden",
				tipAttr : "w_tip"
			},
			requestParamsName : {
				pageSize : "pageSize",
				curPage : "curPage",
				sortName : "sortName",
				sortOrder : "sortOrder"
			},
			beforeSend : function(options, XMLHttpRequest) {
			},
			complete : function(options, XMLHttpRequest, textStatus) {
			},
			processUserdata : function(userdata, options) {
			},
			extend : {
				initGridMethods : {},
				beforeRenderGridMethods : {},
				renderPerRowMethods : {},
				renderPerColumnMethods : {},
				afterRenderGridMethods : {}
			},
			additionalBeforeRenderGrid : function(parseSuccess, gridData,
					options) {
			},
			additionalRenderPerRow : function(record, rowIndex, trObj, options) {
			},
			additionalRenderPerColumn : function(record, rowIndex, colIndex,
					tdObj, trObj, options) {
			},
			additionalAfterRenderGrid : function(parseSuccess, gridData,
					options) {
			}
		},
		gridObjs : {},
		init : function(gridId, settings) {
			if (!$("#" + gridId).hasClass("bsgrid")) {
				$("#" + gridId).addClass("bsgrid")
			}
			var options = {
				settings : $.extend(true, {}, $.fn.bsgrid.defaults, settings),
				gridId : gridId,
				noPagingationId : gridId + "_no_pagination",
				pagingOutTabId : gridId + "_pt_outTab",
				pagingId : gridId + "_pt",
				sortName : "",
				sortOrder : "",
				otherParames : settings.otherParames,
				totalRows : 0,
				totalPages : 0,
				curPage : 1,
				curPageRowsNum : 0,
				startRow : 0,
				endRow : 0
			};
			if ($("#" + gridId).find("thead").length == 0) {
				$("#" + gridId).prepend("<thead></thead>");
				$("#" + gridId).find(
						"tr:lt("
								+ ($("#" + gridId + " tr").length - $("#"
										+ gridId + " tfoot tr").length) + ")")
						.appendTo($("#" + gridId + " thead"))
			}
			if ($("#" + gridId).find("tbody").length == 0) {
				$("#" + gridId + " thead").after("<tbody></tbody>")
			}
			if ($("#" + gridId).find("tfoot").length == 0) {
				$("#" + gridId)
						.append('<tfoot style="display: none;"></tfoot>')
			}
			options.columnsModel = $.fn.bsgrid.initColumnsModel(options);
			if (settings.pageSizeForGrid != undefined) {
				options.settings.pageSizeForGrid = settings.pageSizeForGrid
			}
			options.settings.dataType = options.settings.dataType.toLowerCase();
			if (options.settings.pageSizeSelect) {
				if ($.inArray(options.settings.pageSize,
						options.settings.pageSizeForGrid) == -1) {
					options.settings.pageSizeForGrid
							.push(options.settings.pageSize)
				}
				options.settings.pageSizeForGrid.sort(function(a, b) {
					return a - b
				})
			}
			var gridObj = {
				options : options,
				getPageCondition : function(curPage) {
					return $.fn.bsgrid.getPageCondition(curPage, options)
				},
				page : function(curPage) {
					$.fn.bsgrid.page(curPage, options)
				},
				loadGridData : function(dataType, gridData) {
					$.fn.bsgrid.loadGridData(dataType, gridData, options)
				},
				getSelectedRow : function() {
					return $.fn.bsgrid.getSelectedRow(options)
				},
				selectRow : function(row) {
					return $.fn.bsgrid.selectRow(row, options)
				},
				unSelectRow : function() {
					return $.fn.bsgrid.unSelectRow(options)
				},
				getUserdata : function() {
					return $.fn.bsgrid.getUserdata(options)
				},
				getRowRecord : function(rowObj) {
					return $.fn.bsgrid.getRowRecord(rowObj)
				},
				getRecord : function(row) {
					return $.fn.bsgrid.getRecord(row, options)
				},
				getRecordIndexValue : function(record, index) {
					return $.fn.bsgrid.getRecordIndexValue(record, index,
							options)
				},
				getColumnValue : function(row, index) {
					return $.fn.bsgrid.getColumnValue(row, index, options)
				},
				sort : function(obj) {
					$.fn.bsgrid.sort(obj, options)
				},
				getGridHeaderObject : function() {
					return $.fn.bsgrid.getGridHeaderObject(options)
				},
				getColumnAttr : function(colIndex, attrName) {
					return $.fn.bsgrid.getColumnAttr(colIndex, attrName,
							options)
				},
				appendHeaderSort : function() {
					$.fn.bsgrid.appendHeaderSort(options)
				},
				setGridBlankBody : function() {
					$.fn.bsgrid.setGridBlankBody(options)
				},
				createPagingOutTab : function() {
					$.fn.bsgrid.createPagingOutTab(options)
				},
				clearGridBodyData : function() {
					$.fn.bsgrid.clearGridBodyData(options)
				},
				getCurPage : function() {
					return $.fn.bsgrid.getCurPage(options)
				},
				refreshPage : function() {
					$.fn.bsgrid.refreshPage(options)
				},
				firstPage : function() {
					$.fn.bsgrid.firstPage(options)
				},
				prevPage : function() {
					$.fn.bsgrid.prevPage(options)
				},
				nextPage : function() {
					$.fn.bsgrid.nextPage(options)
				},
				lastPage : function() {
					$.fn.bsgrid.lastPage(options)
				},
				gotoPage : function(goPage) {
					$.fn.bsgrid.gotoPage(options, goPage)
				},
				initPaging : function() {
					return $.fn.bsgrid.initPaging(options)
				},
				setPagingValues : function() {
					$.fn.bsgrid.setPagingValues(options)
				}
			};
			$.fn.bsgrid.gridObjs[gridId] = gridObj;
			if (options.settings.pageAll || options.settings.pageSize < 1) {
				options.settings.pageAll = true;
				options.settings.pageSize = 0
			}
			gridObj.appendHeaderSort();
			gridObj.createPagingOutTab();
			if (!options.settings.pageAll) {
				gridObj.pagingObj = gridObj.initPaging()
			}
			if (options.settings.isProcessLockScreen) {
				$.fn.bsgrid.addLockScreen(options)
			}
			for ( var key in options.settings.extend.initGridMethods) {
				options.settings.extend.initGridMethods[key](gridId, options)
			}
			if (options.settings.autoLoad) {
				setTimeout(function() {
					gridObj.page(1)
				}, 10)
			} else {
				gridObj.setGridBlankBody()
			}
			return gridObj
		},
		initColumnsModel : function(options) {
			var columnsModel = [];
			$.fn.bsgrid
					.getGridHeaderObject(options)
					.each(
							function() {
								var columnModel = {};
								columnModel.sortName = "";
								columnModel.sortOrder = "";
								var sortInfo = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.sortAttr));
								if (sortInfo.length != 0) {
									var sortInfoArray = sortInfo.split(",");
									columnModel.sortName = $
											.trim(sortInfoArray[0]);
									columnModel.sortOrder = $
											.trim(sortInfoArray.length > 1 ? sortInfoArray[1]
													: "")
								}
								columnModel.index = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.indexAttr));
								columnModel.render = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.renderAttr));
								columnModel.tip = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.tipAttr));
								var maxLen = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.lengthAttr));
								columnModel.maxLen = maxLen.length != 0 ? parseInt(maxLen)
										: options.settings.colsProperties.maxLength;
								var align = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.alignAttr));
								columnModel.align = align == "" ? options.settings.colsProperties.align
										: align;
								columnModel.hidden = $
										.trim($(this)
												.attr(
														options.settings.colsProperties.hiddenAttr));
								columnsModel.push(columnModel)
							});
			return columnsModel
		},
		getGridObj : function(gridId) {
			var obj = $.fn.bsgrid.gridObjs[gridId];
			return obj ? obj : null
		},
		buildData : {
			gridData : function(type, curPage, data) {
				if (type == "json") {
					return $.fn.bsgrid.buildJsonData.gridData(curPage, data)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.buildXmlData.gridData(curPage, data)
					}
				}
				return false
			}
		},
		parseData : {
			success : function(type, gridData) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.success(gridData)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.success(gridData)
					}
				}
				return false
			},
			totalRows : function(type, gridData) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.totalRows(gridData)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.totalRows(gridData)
					}
				}
				return false
			},
			curPage : function(type, gridData) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.curPage(gridData)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.curPage(gridData)
					}
				}
				return false
			},
			data : function(type, gridData) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.data(gridData)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.data(gridData)
					}
				}
				return false
			},
			userdata : function(type, gridData) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.userdata(gridData)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.userdata(gridData)
					}
				}
				return false
			},
			getDataLen : function(type, gridData) {
				if (type == "json" || type == "xml") {
					return $.fn.bsgrid.parseData.data(type, gridData).length
				}
				return 0
			},
			getRecord : function(type, data, row) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.getRecord(data, row)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.getRecord(data, row)
					}
				}
				return false
			},
			getColumnValue : function(type, record, index) {
				if (type == "json") {
					return $.fn.bsgrid.parseJsonData.getColumnValue(record,
							index)
				} else {
					if (type == "xml") {
						return $.fn.bsgrid.parseXmlData.getColumnValue(record,
								index)
					}
				}
				return false
			}
		},
		buildJsonData : {
			gridData : function(curPage, data) {
				return {
					success : true,
					totalRows : data.length,
					curPage : curPage,
					data : data
				}
			}
		},
		parseJsonData : {
			success : function(json) {
				return json.success
			},
			totalRows : function(json) {
				return json.totalRows
			},
			curPage : function(json) {
				return json.curPage
			},
			data : function(json) {
				return json.data
			},
			userdata : function(json) {
				return json.userdata
			},
			getRecord : function(data, row) {
				return data[row]
			},
			getColumnValue : function(record, index) {
				return $.trim(record[index])
			}
		},
		buildXmlData : {
			gridData : function(curPage, data) {
				return '<?xml version="1.0" encoding="UTF-8"?><gridData><success>true</success><totalRows>'
						+ $("<xml>" + data + "</xml>").find("row").length
						+ "</totalRows><curPage>"
						+ curPage
						+ "</curPage><data>" + data + "</data></gridData>"
			}
		},
		parseXmlData : {
			success : function(xml) {
				return $.trim($(xml).find("gridData success").text()) == "true"
			},
			totalRows : function(xml) {
				return parseInt($(xml).find("gridData totalRows").text())
			},
			curPage : function(xml) {
				return parseInt($(xml).find("gridData curPage").text())
			},
			data : function(xml) {
				return $(xml).find("gridData data row")
			},
			userdata : function(xml) {
				return $(xml).find("gridData userdata")
			},
			getRecord : function(data, row) {
				return data.eq(row)
			},
			getColumnValue : function(record, index) {
				return $.trim(record.find(index).text())
			}
		},
		getPageCondition : function(curPage, options) {
			var params = new StringBuilder();
			if (options.otherParames == false) {
			} else {
				if ((typeof options.otherParames).toLowerCase() == "string"
						|| options.otherParames instanceof String) {
					params.append("&" + options.otherParames)
				} else {
					if (options.otherParames instanceof Array) {
						$.each(options.otherParames, function(i, objVal) {
							params.append("&" + objVal.name + "="
									+ objVal.value)
						})
					} else {
						for ( var key in options.otherParames) {
							params.append("&" + key + "="
									+ options.otherParames[key])
						}
					}
				}
			}
			var condition = params.length == 0 ? "" : params.toString()
					.substring(1);
			condition += (condition.length == 0 ? "" : "&")
					+ options.settings.requestParamsName.pageSize + "="
					+ options.settings.pageSize + "&"
					+ options.settings.requestParamsName.curPage + "="
					+ curPage + "&"
					+ options.settings.requestParamsName.sortName + "="
					+ options.sortName + "&"
					+ options.settings.requestParamsName.sortOrder + "="
					+ options.sortOrder;
			return condition
		},
		page : function(curPage, options) {
			if ($.trim(curPage) == "" || isNaN(curPage)) {
				alert($.bsgridLanguage.needInteger);
				return
			}
			var dataType = options.settings.dataType;
			if (options.settings.localData != false) {
				if (dataType == "json") {
					$.fn.bsgrid.loadGridData(dataType, $.fn.bsgrid.buildData
							.gridData(dataType, curPage,
									options.settings.localData), options)
				} else {
					if (dataType == "xml") {
						$.fn.bsgrid.loadGridData(dataType, "<xml>"
								+ $.fn.bsgrid.buildData.gridData(dataType,
										curPage, options.settings.localData)
								+ "</xml>", options)
					}
				}
				return
			}
			$.ajax({
				type : "post",
				url : options.settings.url,
				data : $.fn.bsgrid.getPageCondition(curPage, options),
				dataType : dataType,
				beforeSend : function(XMLHttpRequest) {
					if (options.settings.isProcessLockScreen) {
						$.fn.bsgrid.lockScreen(options)
					}
					options.settings.beforeSend(options, XMLHttpRequest)
				},
				complete : function(XMLHttpRequest, textStatus) {
					options.settings.complete(options, XMLHttpRequest,
							textStatus);
					if (options.settings.isProcessLockScreen) {
						$.fn.bsgrid.unlockScreen(options)
					}
				},
				success : function(gridData, textStatus) {
					$.fn.bsgrid.loadGridData(dataType, gridData, options)
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//alert($.bsgridLanguage.errorForSendOrRequestData)
				}
			})
		},
		loadGridData : function(dataType, gridData, options) {
			var parseSuccess = $.fn.bsgrid.parseData
					.success(dataType, gridData);
			for ( var key in options.settings.extend.beforeRenderGridMethods) {
				options.settings.extend.beforeRenderGridMethods[key](
						parseSuccess, gridData, options)
			}
			options.settings.additionalBeforeRenderGrid(parseSuccess, gridData,
					options);
			if (parseSuccess) {
				var userdata = $.fn.bsgrid.parseData.userdata(dataType,
						gridData);
				$.fn.bsgrid.storeUserdata(userdata, options);
				options.settings.processUserdata(userdata, options);
				var totalRows = parseInt($.fn.bsgrid.parseData.totalRows(
						dataType, gridData));
				var curPage = parseInt($.fn.bsgrid.parseData.curPage(dataType,
						gridData));
				curPage = Math.max(curPage, 1);
				if (options.settings.pageAll) {
					curPage = 1;
					options.settings.pageSize = totalRows;
					$("#" + options.noPagingationId).html(totalRows)
				}
				var pageSize = options.settings.pageSize;
				var totalPages = parseInt(totalRows / pageSize);
				totalPages = parseInt((totalRows % pageSize == 0) ? totalPages
						: totalPages + 1);
				var curPageRowsNum = $.fn.bsgrid.parseData.getDataLen(dataType,
						gridData);
				curPageRowsNum = curPageRowsNum > pageSize ? pageSize
						: curPageRowsNum;
				curPageRowsNum = (curPage * pageSize < totalRows) ? curPageRowsNum
						: (totalRows - (curPage - 1) * pageSize);
				var startRow = (curPage - 1) * pageSize + 1;
				var endRow = startRow + curPageRowsNum - 1;
				startRow = curPageRowsNum <= 0 ? 0 : startRow;
				endRow = curPageRowsNum <= 0 ? 0 : endRow;
				options.totalRows = totalRows;
				options.totalPages = totalPages;
				options.curPage = curPage;
				options.curPageRowsNum = curPageRowsNum;
				options.startRow = startRow;
				options.endRow = endRow;
				if (!options.settings.pageAll) {
					$.fn.bsgrid.setPagingValues(options)
				}
				if (options.settings.displayPagingToolbarOnlyMultiPages
						&& totalPages <= 1) {
					$("#" + options.pagingId).hide();
					$("#" + options.pagingOutTabId).hide()
				} else {
					$("#" + options.pagingOutTabId).show();
					$("#" + options.pagingId).show()
				}
				$.fn.bsgrid.setGridBlankBody(options);
				if (curPageRowsNum == 0) {
					return
				}
				var data = $.fn.bsgrid.parseData.data(dataType, gridData);
				var dataLen = data.length;
				$.fn.bsgrid.addRowsClickEvent(options);
				$("#" + options.gridId + " tbody tr")
						.each(
								function(i) {
									var trObj = $(this);
									var record = null;
									if (i < curPageRowsNum) {
										record = $.fn.bsgrid.parseData
												.getRecord(
														dataType,
														data,
														dataLen != totalRows ? i
																: startRow + i
																		- 1)
									}
									$.fn.bsgrid
											.storeRowData(i, record, options);
									for ( var key in options.settings.extend.renderPerRowMethods) {
										options.settings.extend.renderPerRowMethods[key]
												(record, i, trObj, options)
									}
									options.settings.additionalRenderPerRow(
											record, i, trObj, options);
									var columnsModel = options.columnsModel;
									$(this)
											.find("td")
											.each(
													function(j) {
														if (i < curPageRowsNum) {
															var index = columnsModel[j].index;
															var render = columnsModel[j].render;
															if (render != "") {
																var render_method = eval(render);
																var render_html = render_method(
																		record,
																		i, j,
																		options);
																$(this)
																		.html(
																				render_html)
															} else {
																if (index != "") {
																	var value = $.fn.bsgrid.parseData
																			.getColumnValue(
																					dataType,
																					record,
																					index);
																	if (columnsModel[j].tip == "true") {
																		$.fn.bsgrid
																				.columnTip(
																						this,
																						value,
																						record)
																	}
																	if (options.settings.longLengthAotoSubAndTip) {
																		$.fn.bsgrid
																				.longLengthSubAndTip(
																						this,
																						value,
																						columnsModel[j].maxLen,
																						record)
																	} else {
																		$(this)
																				.html(
																						value)
																	}
																}
															}
														} else {
															$(this).html(
																	"&nbsp;")
														}
														for ( var key in options.settings.extend.renderPerColumnMethods) {
															var renderPerColumn_html = options.settings.extend.renderPerColumnMethods[key]
																	(
																			record,
																			i,
																			j,
																			$(this),
																			trObj,
																			options);
															if (renderPerColumn_html != null
																	&& renderPerColumn_html != false) {
																$(this)
																		.html(
																				renderPerColumn_html)
															}
														}
														options.settings
																.additionalRenderPerColumn(
																		record,
																		i,
																		j,
																		$(this),
																		trObj,
																		options)
													})
								})
			} else {
				alert($.bsgridLanguage.errorForRequestData)
			}
			for ( var key in options.settings.extend.afterRenderGridMethods) {
				options.settings.extend.afterRenderGridMethods[key](
						parseSuccess, gridData, options)
			}
			options.settings.additionalAfterRenderGrid(parseSuccess, gridData,
					options)
		},
		addRowsClickEvent : function(options) {
			$(
					"#" + options.gridId + " tbody tr:lt("
							+ options.curPageRowsNum + ")").click(
					function() {
						if ($(this).hasClass("selected")) {
							$(this).removeClass("selected").removeClass(
									"selected_color")
						} else {
							$.fn.bsgrid.unSelectRow(options);
							$(this).addClass("selected");
							if (options.settings.changeColorIfRowSelected) {
								$(this).addClass("selected_color")
							}
						}
					})
		},
		getSelectedRow : function(options) {
			return $("#" + options.gridId + " tbody tr.selected")
		},
		selectRow : function(row, options) {
			$.fn.bsgrid.unSelectRow(options);
			var trObj = $("#" + options.gridId + " tbody tr:eq(" + row + ")");
			trObj.addClass("selected");
			if (options.settings.changeColorIfRowSelected) {
				trObj.addClass("selected_color")
			}
		},
		unSelectRow : function(options) {
			$.fn.bsgrid.getSelectedRow(options).removeClass("selected")
					.removeClass("selected_color")
		},
		getUserdata : function(options) {
			$("#" + options.gridId).data("userdata")
		},
		storeUserdata : function(userdata, options) {
			$("#" + options.gridId).data("userdata", userdata)
		},
		getRowRecord : function(rowObj) {
			return rowObj.data("record")
		},
		storeRowData : function(row, record, options) {
			$("#" + options.gridId + " tbody tr:eq(" + row + ")").data(
					"record", record)
		},
		getRecord : function(row, options) {
			var record = $("#" + options.gridId + " tbody tr:eq(" + row + ")")
					.data("record");
			return record == undefined ? null : record
		},
		getRecordIndexValue : function(record, index, options) {
			if (record == null) {
				return ""
			} else {
				return $.fn.bsgrid.parseData.getColumnValue(
						options.settings.dataType, record, index)
			}
		},
		getColumnValue : function(row, index, options) {
			var record = $.fn.bsgrid.getRecord(row, options);
			return $.fn.bsgrid.getRecordIndexValue(record, index, options)
		},
		sort : function(obj, options) {
			options.sortName = "";
			options.sortOrder = "";
			var aObj = $(obj).find("a");
			var field = $(aObj).attr("sortName");
			var columnsModel = options.columnsModel;
			$.fn.bsgrid
					.getGridHeaderObject(options)
					.each(
							function(i) {
								var sortName = columnsModel[i].sortName;
								if (sortName != "") {
									var sortOrder = $.fn.bsgrid.getSortOrder(
											$(this), options);
									if (!options.settings.multiSort
											&& sortName != field) {
										$(this).find("a").attr("class",
												"sort sort-view")
									} else {
										if (sortName == field) {
											if (sortOrder == "") {
												sortOrder = "desc"
											} else {
												if (sortOrder == "desc") {
													sortOrder = "asc"
												} else {
													if (sortOrder == "asc") {
														sortOrder = ""
													}
												}
											}
											$(this)
													.find("a")
													.attr(
															"class",
															"sort sort-"
																	+ (sortOrder == "" ? "view"
																			: sortOrder))
										}
										if (sortOrder != "") {
											options.sortName = ($
													.trim(options.sortName) == "") ? sortName
													: (options.sortName + "," + sortName);
											options.sortOrder = ($
													.trim(options.sortOrder) == "") ? sortOrder
													: (options.sortOrder + "," + sortOrder)
										}
									}
								}
							});
			$.fn.bsgrid.refreshPage(options)
		},
		getSortOrder : function(obj, options) {
			var sortOrder = $.trim($(obj).find("a").attr("class"));
			if (sortOrder == "sort sort-view") {
				sortOrder = ""
			} else {
				if (sortOrder == "sort sort-asc") {
					sortOrder = "asc"
				} else {
					if (sortOrder == "sort sort-desc") {
						sortOrder = "desc"
					} else {
						sortOrder = ""
					}
				}
			}
			return sortOrder
		},
		getGridHeaderObject : function(options) {
			return $("#" + options.gridId + " thead tr:last").find("th")
		},
		getColumnAttr : function(colIndex, attrName, options) {
			return $.trim($.fn.bsgrid.getGridHeaderObject(options).eq(colIndex)
					.attr(attrName))
		},
		appendHeaderSort : function(options) {
			var columnsModel = options.columnsModel;
			$.fn.bsgrid
					.getGridHeaderObject(options)
					.each(
							function(i) {
								if (columnsModel[i].sortName != "") {
									var sortName = columnsModel[i].sortName;
									var sortOrder = columnsModel[i].sortOrder;
									var sortHtml = '<a href="#" sortName="'
											+ sortName + '" class="sort ';
									if (sortOrder != ""
											&& (sortOrder == "desc" || sortOrder == "asc")) {
										options.sortName = ($
												.trim(options.sortName) == "") ? sortName
												: (options.sortName + "," + sortName);
										options.sortOrder = ($
												.trim(options.sortOrder) == "") ? sortOrder
												: (options.sortOrder + "," + sortOrder);
										sortHtml += "sort-" + sortOrder
									} else {
										sortHtml += "sort-view"
									}
									sortHtml += '">&nbsp;&nbsp;&nbsp;</a>';
									$(this).append(sortHtml).find(".sort")
											.click(
													function() {
														$.fn.bsgrid.sort(
																$(this).parent(
																		"th"),
																options)
													})
								}
							})
		},
		setGridBlankBody : function(options) {
			$("#" + options.gridId + " tbody tr").remove();
			var header = $.fn.bsgrid.getGridHeaderObject(options);
			var rowSb = "";
			if (options.settings.pageSize > 0) {
				var columnsModel = options.columnsModel;
				var trSb = new StringBuilder();
				trSb.append("<tr>");
				for (var hi = 0; hi < header.length; hi++) {
					trSb.append('<td style="text-align: '
							+ columnsModel[hi].align + ";");
					if (columnsModel[hi].hidden == "true") {
						header.eq(hi).css("display", "none");
						trSb.append(" display: none;")
					}
					trSb.append('"');
					trSb.append("></td>")
				}
				trSb.append("</tr>");
				rowSb = trSb.toString()
			}
			var rowsSb = new StringBuilder();
			var curPageRowsNum = options.settings.pageSize;
			if (!options.settings.displayBlankRows) {
				curPageRowsNum = options.endRow - options.startRow + 1;
				curPageRowsNum = options.endRow > 0 ? curPageRowsNum : 0
			}
			if (curPageRowsNum == 0) {
				rowsSb.append('<tr><td colspan="' + header.length + '">'
						+ $.bsgridLanguage.noDataToDisplay + "</td></tr>")
			} else {
				for (var pi = 0; pi < curPageRowsNum; pi++) {
					rowsSb.append(rowSb)
				}
			}
			$("#" + options.gridId + " tbody").append(rowsSb.toString());
			if (curPageRowsNum != 0) {
				if (options.settings.stripeRows) {
					$("#" + options.gridId + " tbody tr:even").addClass(
							"even_index_row")
				}
			}
			if (!options.settings.lineWrap) {
				$("#" + options.gridId + " tbody tr td").addClass("lineNoWrap")
			} else {
				$("#" + options.gridId + " tbody tr td").addClass("lineWrap")
			}
		},
		createPagingOutTab : function(options) {
			var pagingOutTabSb = new StringBuilder();
			if(options.settings.pageAll){
				pagingOutTabSb.append('<table id="'
						+ options.pagingOutTabId
						+ '" class="bsgridPagingOutTab" style="display: none;"></table>');
			}else{
				pagingOutTabSb.append('<table id="'
						+ options.pagingOutTabId
						+ '" class="bsgridPagingOutTab" style="display: none;"><tr><td align="'
						+ options.settings.pagingToolbarAlign + '">');
				if (options.settings.pageAll) {
					pagingOutTabSb.append($.bsgridLanguage
							.noPagingation(options.noPagingationId)
							+ "&nbsp;&nbsp;&nbsp;")
				}
				pagingOutTabSb.append("</td></tr></table>");
			}
			$("#" + options.gridId).after(pagingOutTabSb.toString());
		},
		clearGridBodyData : function(options) {
			$("#" + options.gridId + " tbody tr td").html("&nbsp;")
		},
		addLockScreen : function(options) {
			if ($(".bsgrid.lockscreen").length == 0) {
				var lockScreenHtml = new StringBuilder();
				lockScreenHtml
						.append('<div class="bsgrid lockscreen" times="0">');
				lockScreenHtml.append("</div>");
				lockScreenHtml.append('<div class="bsgrid loading_div">');
				lockScreenHtml
						.append('<table><tr><td><center><div class="bsgrid loading"><span>&nbsp;&emsp;</span>&nbsp;'
								+ $.bsgridLanguage.loadingDataMessage
								+ "&emsp;<center></div></td></tr></table>");
				lockScreenHtml.append("</div>");
				$("body").append(lockScreenHtml.toString())
			}
		},
		lockScreen : function(options) {
			$(".bsgrid.lockscreen").attr("times",
					parseInt($(".bsgrid.lockscreen").attr("times")) + 1);
			if ($(".bsgrid.lockscreen").css("display") == "none") {
				$(".bsgrid.lockscreen").show();
				$(".bsgrid.loading_div").show()
			}
		},
		unlockScreen : function(options) {
			$(".bsgrid.lockscreen").attr("times",
					parseInt($(".bsgrid.lockscreen").attr("times")) - 1);
			if ($(".bsgrid.lockscreen").attr("times") == "0") {
				setTimeout(function() {
					$(".bsgrid.lockscreen").hide();
					$(".bsgrid.loading_div").hide()
				}, 50)
			}
		},
		columnTip : function(obj, value, record) {
			$(obj).attr("title", value)
		},
		longLengthSubAndTip : function(obj, value, maxLen, record) {
			var tip = false;
			if (value.length > maxLen) {
				try {
					if (value.indexOf("<") < 0 || value.indexOf(">") < 2
							|| $(value).text().length == 0) {
						tip = true
					}
				} catch (e) {
					tip = true
				}
			}
			if (tip) {
				$(obj).html(value.substring(0, maxLen - 3) + "...");
				$.fn.bsgrid.columnTip(obj, value, record)
			} else {
				$(obj).html(value)
			}
		},
		getCurPage : function(options) {
			return $.fn.bsgrid.getGridObj(options.gridId).pagingObj
					.getCurPage()
		},
		refreshPage : function(options) {
			if (!options.settings.pageAll) {
				$.fn.bsgrid.getGridObj(options.gridId).pagingObj.refreshPage()
			} else {
				$.fn.bsgrid.page(1, options)
			}
		},
		firstPage : function(options) {
			$.fn.bsgrid.getGridObj(options.gridId).pagingObj.firstPage()
		},
		prevPage : function(options) {
			$.fn.bsgrid.getGridObj(options.gridId).pagingObj.prevPage()
		},
		nextPage : function(options) {
			$.fn.bsgrid.getGridObj(options.gridId).pagingObj.nextPage()
		},
		lastPage : function(options) {
			$.fn.bsgrid.getGridObj(options.gridId).pagingObj.lastPage()
		},
		gotoPage : function(options, goPage) {
			$.fn.bsgrid.getGridObj(options.gridId).pagingObj.gotoPage(goPage)
		},
		initPaging : function(options) {
			$("#" + options.pagingOutTabId + " td")
					.attr("id", options.pagingId);
			return $.fn.bsgrid_paging
					.init(
							options.pagingId,
							{
								gridId : options.gridId,
								pageSize : options.settings.pageSize,
								pageSizeSelect : options.settings.pageSizeSelect,
								pageSizeForGrid : options.settings.pageSizeForGrid,
								pageIncorrectTurnAlert : options.settings.pageIncorrectTurnAlert,
								pagingLittleToolbar : options.settings.pagingLittleToolbar,
								pagingBtnClass : options.settings.pagingBtnClass
							})
		},
		setPagingValues : function(options) {
			$.fn.bsgrid.getGridObj(options.gridId).pagingObj.setPagingValues(
					options.curPage, options.totalRows)
		}
	}
})(jQuery);
(function(a) {
	a.fn.bsgrid.defaults.extend.settings = {
		supportGridEdit : false,
		supportGridEditTriggerEvent : "rowClick",
		supportColumnMove : false,
		searchConditionsContainerId : "",
		fixedGridHeader : false,
		fixedGridHeight : "320px"
	};
	a.fn.bsgrid.defaults.colsProperties.lineNumberAttr = "w_num";
	a.fn.bsgrid.defaults.colsProperties.checkAttr = "w_check";
	a.fn.bsgrid.defaults.colsProperties.editAttr = "w_edit";
	a.fn.bsgrid.defaults.colsProperties.aggAttr = "w_agg";
	a.fn.bsgrid.extendInitGrid = {};
	a.fn.bsgrid.extendBeforeRenderGrid = {};
	a.fn.bsgrid.extendRenderPerRow = {};
	a.fn.bsgrid.extendRenderPerColumn = {};
	a.fn.bsgrid.extendAfterRenderGrid = {};
	a.fn.bsgrid.extendOtherMethods = {};
	a.bsgrid
			.forcePushPropertyInObject(
					a.fn.bsgrid.defaults.extend.initGridMethods,
					"initGridOptions",
					function(b, c) {
						var d = c.columnsModel;
						a.fn.bsgrid
								.getGridHeaderObject(c)
								.each(
										function(e) {
											d[e].lineNumber = a
													.trim(a(this)
															.attr(
																	c.settings.colsProperties.lineNumberAttr));
											d[e].check = a
													.trim(a(this)
															.attr(
																	c.settings.colsProperties.checkAttr));
											d[e].edit = a
													.trim(a(this)
															.attr(
																	c.settings.colsProperties.editAttr))
										});
						if (a("#" + c.gridId + " tfoot tr td["
								+ c.settings.colsProperties.aggAttr + "!='']").length != 0) {
							a("#" + c.gridId + " tfoot tr td")
									.each(
											function(f) {
												d[f].aggName = "";
												d[f].aggIndex = "";
												var e = a
														.trim(a(this)
																.attr(
																		c.settings.colsProperties.aggAttr));
												if (e.length != 0) {
													var g = e.split(",");
													d[f].aggName = g[0]
															.toLocaleLowerCase();
													d[f].aggIndex = g.length > 1 ? g[1]
															: ""
												}
											})
						}
						if (a("#" + c.gridId + " thead tr:last th["
								+ c.settings.colsProperties.checkAttr + "!='']").length == 0) {
							delete c.settings.extend.initGridMethods.initGridCheck;
							delete c.settings.extend.renderPerColumnMethods.renderCheck;
							delete c.settings.extend.afterRenderGridMethods.addCheckChangedEvent
						}
						if (!c.settings.extend.settings.supportGridEdit) {
							delete c.settings.extend.renderPerColumnMethods.renderForm;
							delete c.settings.extend.afterRenderGridMethods.addGridEditEvent
						}
						if (!c.settings.extend.settings.supportColumnMove) {
							delete c.settings.extend.initGridMethods.initColumnMove
						}
						if (!c.settings.extend.settings.fixedGridHeader) {
							delete c.settings.extend.initGridMethods.fixedHeader;
							delete c.settings.extend.afterRenderGridMethods.fixedHeader
						}
						if (a
								.trim(c.settings.extend.settings.searchConditionsContainerId) == "") {
							delete c.settings.extend.initGridMethods.initSearchConditions
						}
						if (a("#" + c.gridId + " tfoot td["
								+ c.settings.colsProperties.aggAttr + "!='']").length == 0) {
							delete c.settings.extend.afterRenderGridMethods.aggregation
						}
					});
	a.fn.bsgrid.extendOtherMethods.fixedHeader = function(b, c) {
		if (a.trim(a("#" + c.gridId + "_fixedDiv").data("fixedGridLock")) == "lock") {
			return
		}
		a("#" + c.gridId + "_fixedDiv").data("fixedGridLock", "lock");
		var e = a("#" + c.gridId + " thead tr").length;
		if (!b) {
			e = e / 2;
			a("#" + c.gridId + " thead tr:lt(" + e + ")").remove()
		}
		var d = f(c.settings.extend.settings.fixedGridHeight);
		if (d < a("#" + c.gridId).height()) {
			a("#" + c.gridId + "_fixedDiv").height(d);
			a("#" + c.gridId).width(
					a("#" + c.gridId + "_fixedDiv").width() - 18);
			a("#" + c.gridId + "_fixedDiv").animate({
				scrollTop : "0px"
			}, 0)
		} else {
			a("#" + c.gridId + "_fixedDiv").height(a("#" + c.gridId).height());
			a("#" + c.gridId)
					.width(a("#" + c.gridId + "_fixedDiv").width() - 1)
		}
		a("#" + c.gridId + " thead tr:lt(" + e + ")").clone(true).prependTo(
				"#" + c.gridId + " thead");
		a("#" + c.gridId + " thead tr:lt(" + e + ")").css({
			"z-index" : 10,
			position : "fixed"
		}).width(a("#" + c.gridId + " thead tr:last").width());
		a("#" + c.gridId + " thead tr:lt(" + e + ")").each(
				function(h) {
					var g = a("#" + c.gridId + " thead tr:eq(" + (e + h) + ")")
							.position();
					a(this).css(
							{
								top : g.top
										- f(a(this).find("th").css(
												"border-top-width")),
								left : g.left
							})
				});
		a("#" + c.gridId + " thead tr:gt(" + (e - 1) + ")")
				.each(
						function(g) {
							a(this)
									.find("th")
									.each(
											function(j) {
												var h = a(this);
												a(
														"#"
																+ c.gridId
																+ " thead tr:eq("
																+ g
																+ ") th:eq("
																+ j + ")")
														.height(
																h.height()
																		+ ((g == e - 1) ? 2
																				: 1)
																		* f(h
																				.css("border-top-width")))
														.width(
																h.width()
																		+ f(h
																				.css("border-left-width")))
											})
						});
		a("#" + c.gridId + "_fixedDiv").data("fixedGridLock", "");
		function f(h) {
			h = a.trim(h).toLowerCase().replace("px", "");
			var g = parseFloat(h);
			return isNaN(g) ? 0 : g
		}
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.initGridMethods, "fixedHeader",
			function(b, c) {
				a("#" + b).wrap('<div id="' + b + '_fixedDiv"></div>');
				a("#" + b + "_fixedDiv").data("fixedGridLock", "");
				a("#" + b + "_fixedDiv").css({
					padding : 0,
					"border-width" : 0,
					width : "98%",
					"overflow-y" : "auto",
					"margin-bottom" : "-1px"
				});
				a("#" + b).css({
					width : "auto"
				});
				a("#" + b + "_pt_outTab").css({
					"border-top-width" : "1px"
				});
				a.fn.bsgrid.extendOtherMethods.fixedHeader(true, c);
				a(window).resize(function() {
					a.fn.bsgrid.extendOtherMethods.fixedHeader(false, c)
				})
			});
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.afterRenderGridMethods, "fixedHeader",
			function(d, c, b) {
				a.fn.bsgrid.extendOtherMethods.fixedHeader(false, b)
			});
	a.fn.bsgrid.extendInitGrid.bindExtendMethods = function(b, c) {
		var d = a.fn.bsgrid.getGridObj(b);
		if (a("#" + c.gridId + " thead tr:last th["
				+ c.settings.colsProperties.checkAttr + "!='']").length != 0) {
			d.getCheckedRowsIndexs = function() {
				return a.fn.bsgrid.defaults.extend.getCheckedRowsIndexs(c)
			};
			d.getCheckedRowsRecords = function() {
				return a.fn.bsgrid.defaults.extend.getCheckedRowsRecords(c)
			}
		}
		if (c.settings.extend.settings.supportGridEdit) {
			d.activeGridEditMode = function() {
				return a.fn.bsgrid.defaults.extend.activeGridEditMode(c)
			};
			d.getChangedRowsIndexs = function() {
				return a.fn.bsgrid.defaults.extend.getChangedRowsIndexs(c)
			};
			d.getChangedRowsOldRecords = function() {
				return a.fn.bsgrid.defaults.extend.getChangedRowsOldRecords(c)
			};
			d.getRowsChangedColumnsValue = function() {
				return a.fn.bsgrid.defaults.extend
						.getRowsChangedColumnsValue(c)
			}
		}
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.initGridMethods, "bindExtendMethods",
			a.fn.bsgrid.extendInitGrid.bindExtendMethods);
	a.fn.bsgrid.extendInitGrid.initGridCheck = function(b, c) {
		a.fn.bsgrid
				.getGridHeaderObject(c)
				.each(
						function(d) {
							if (c.columnsModel[d].check == "true") {
								if (a.trim(a(this).html()) == "") {
									a(this)
											.html(
													'<input class="bsgrid_editgrid_check" type="checkbox"/>')
								}
								a(this)
										.find("input[type=checkbox]")
										.change(
												function() {
													var e = a.bsgrid
															.adaptAttrOrProp(
																	a(this),
																	"checked") ? true
															: false;
													a.bsgrid
															.adaptAttrOrProp(
																	a("#"
																			+ b
																			+ " tbody tr td:nth-child("
																			+ (d + 1)
																			+ ")>input[type=checkbox]"),
																	"checked",
																	e)
												})
							}
						})
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.initGridMethods, "initGridCheck",
			a.fn.bsgrid.extendInitGrid.initGridCheck);
	a.fn.bsgrid.extendInitGrid.initSearchConditions = function(b, c) {
		var e = new StringBuilder();
		e.append('<select class="bsgrid_conditions_select">');
		var f = {};
		a.fn.bsgrid.getGridHeaderObject(c).each(function(h) {
			var g = c.columnsModel[h].index;
			var j = a.trim(a(this).text());
			if (g != "" && j != "" && a.trim(f[g]) == "") {
				f[g] = j
			}
		});
		for ( var d in f) {
			e.append('<option value="' + d + '">' + f[d] + "</option>")
		}
		e.append("</select>");
		e.append("&nbsp;");
		e.append('<input type="text" class="bsgrid_conditions_input" />');
		a("#" + c.settings.extend.settings.searchConditionsContainerId).html(
				e.toString());
		a(
				"#" + c.settings.extend.settings.searchConditionsContainerId
						+ " select.bsgrid_conditions_select").change(
				function() {
					a(this).next("input.bsgrid_conditions_input").attr("name",
							a(this).val())
				}).trigger("change")
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.initGridMethods,
			"initSearchConditions",
			a.fn.bsgrid.extendInitGrid.initSearchConditions);
	a.fn.bsgrid.extendInitGrid.initColumnMove = function(c, d) {
		if (a("#" + d.gridId + " thead tr").length != 1) {
			return
		}
		a("#" + d.gridId).css({
			"table-layout" : "fixed"
		});
		var e = a.fn.bsgrid.getGridHeaderObject(d);
		var b = e.length;
		e
				.each(function(g) {
					var h = this;
					a(h).mousedown(function() {
						f(h, g, b)
					});
					a(h)
							.mousemove(
									function() {
										var q = a(h).offset().left;
										var n = 0, o = 0;
										if (g != b - 1) {
											n = a(h).next();
											o = n.offset().left
										}
										var p = h;
										if (g != b - 1
												&& event.clientX - o > -10) {
											p = n
										}
										if ((g != 0 && event.clientX - q < 10)
												|| (g != b - 1 && event.clientX
														- o > -10)) {
											a(h).css({
												cursor : "e-resize"
											});
											if (a.trim(a(h)
													.data("ex_mousedown")) != "mousedown") {
												return
											}
											var l = a(p).width();
											var m = l - event.clientX
													+ a(p).offset().left;
											var k = a(p).prev().width();
											var i = k + event.clientX
													- a(p).offset().left;
											if (parseInt(m) > 19
													&& parseInt(i) > 19) {
												a(p).width(m).prev().width(i)
											}
										} else {
											a(p).css({
												cursor : "default"
											});
											j(h, g, b)
										}
									});
					a(h).mouseup(function() {
						j(h, g, b)
					});
					a(h)
							.mouseout(
									function() {
										var i = a(h).offset();
										if (i.top > event.clientY
												|| i.top + a(h).height() < event.clientY) {
											j(h, g, b)
										}
									});
					function f(m, l, k) {
						if (l != 0) {
							a(m).prev().data("ex_mousedown", "mousedown")
						}
						a(m).data("ex_mousedown", "mousedown");
						if (l != k - 1) {
							a(m).next().data("ex_mousedown", "mousedown")
						}
					}
					function j(m, l, k) {
						if (l != 0) {
							a(m).prev().data("ex_mousedown", "")
						}
						a(m).data("ex_mousedown", "");
						if (l != k - 1) {
							a(m).next().data("ex_mousedown", "")
						}
					}
				})
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.initGridMethods, "initColumnMove",
			a.fn.bsgrid.extendInitGrid.initColumnMove);
	a.fn.bsgrid.extendRenderPerColumn.renderCheck = function(b, h, d, e, g, c) {
		if (h < c.curPageRowsNum) {
			var f = c.columnsModel[d];
			if (f.check == "true") {
				return '<input class="bsgrid_editgrid_check" type="checkbox" value="'
						+ a.fn.bsgrid.getRecordIndexValue(b, f.index, c)
						+ '"/>'
			}
		}
		return false
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.renderPerColumnMethods, "renderCheck",
			a.fn.bsgrid.extendRenderPerColumn.renderCheck);
	a.fn.bsgrid.extendRenderPerColumn.renderForm = function(c, f, i, b, e, j) {
		if (f < j.curPageRowsNum) {
			var d = j.columnsModel[i];
			var h = d.edit;
			var g = a.fn.bsgrid.getRecordIndexValue(c, d.index, j);
			if (h == "checkbox") {
				return g
						+ '<input class="bsgrid_editgrid_hidden bsgrid_editgrid_checkbox" type="'
						+ h + '" value="' + g + '"/>'
			} else {
				if (h == "text" || h == "hidden" || h == "password"
						|| h == "radio" || h == "button") {
					return g
							+ '<input class="bsgrid_editgrid_hidden bsgrid_editgrid_edit" type="'
							+ h + '" value="' + g + '"/>'
				} else {
					if (h == "textarea") {
						return g
								+ '<textarea class="bsgrid_editgrid_hidden bsgrid_editgrid_edit">'
								+ g + "</textarea>"
					}
				}
			}
		}
		return false
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.renderPerColumnMethods, "renderForm",
			a.fn.bsgrid.extendRenderPerColumn.renderForm);
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.afterRenderGridMethods,
			"renderLineNumber", function(d, c, b) {
				a.fn.bsgrid.getGridHeaderObject(b).each(
						function(f) {
							var e = b.columnsModel[f].lineNumber;
							if (e == "line" || e == "total_line") {
								a(
										"#" + b.gridId + " tbody tr:lt("
												+ b.curPageRowsNum
												+ ") td:nth-child(" + (f + 1)
												+ ")").each(
										function(g) {
											a(this).html(
													(e == "line") ? (g + 1)
															: (g + b.startRow))
										})
							}
						})
			});
	a.fn.bsgrid.extendAfterRenderGrid.addCheckChangedEvent = function(d, c, b) {
		a.fn.bsgrid
				.getGridHeaderObject(b)
				.each(
						function(e) {
							if (b.columnsModel[e].checkAttr == "true") {
								var g = a(this).find("input[type=checkbox]");
								var f = a("#" + b.gridId
										+ " tbody tr td:nth-child(" + (e + 1)
										+ ")>input[type=checkbox]");
								f
										.change(function() {
											var h = a.bsgrid.adaptAttrOrProp(g,
													"checked") ? true : false;
											if (!h
													&& f.filter(":checked").length == f.length) {
												a.bsgrid.adaptAttrOrProp(g,
														"checked", true)
											} else {
												if (h
														&& f.filter(":checked").length != f.length) {
													a.bsgrid.adaptAttrOrProp(g,
															"checked", false)
												}
											}
										})
							}
						})
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.afterRenderGridMethods,
			"addCheckChangedEvent",
			a.fn.bsgrid.extendAfterRenderGrid.addCheckChangedEvent);
	a.fn.bsgrid.extendAfterRenderGrid.addGridEditEvent = function(e, c, b) {
		a("#" + b.gridId + " tbody tr:lt(" + b.curPageRowsNum + ")")
				.each(
						function(f) {
							var g = this;
							var h = b.columnsModel;
							a(g)
									.find("td")
									.each(
											function(i) {
												if (h[i].edit != "") {
													a(this)
															.find(
																	".bsgrid_editgrid_checkbox, .bsgrid_editgrid_edit")
															.change(
																	function() {
																		if (a
																				.trim(a(
																						this)
																						.val()) != a.fn.bsgrid
																				.getGridObj(
																						b.gridId)
																				.getColumnValue(
																						f,
																						h[i].index)) {
																			a(
																					this)
																					.addClass(
																							"bsgrid_editgrid_change")
																		} else {
																			a(
																					this)
																					.removeClass(
																							"bsgrid_editgrid_change")
																		}
																		a(g)
																				.data(
																						"change",
																						a(
																								g)
																								.find(
																										".bsgrid_editgrid_change").length)
																	})
												}
											});
							if (b.settings.extend.settings.supportGridEditTriggerEvent == "") {
								a(this).find(".bsgrid_editgrid_hidden").each(
										function() {
											d(this)
										})
							} else {
								if (b.settings.extend.settings.supportGridEditTriggerEvent == "rowClick") {
									a(this)
											.click(
													function() {
														a(this)
																.find(
																		".bsgrid_editgrid_hidden")
																.each(
																		function() {
																			d(this)
																		})
													})
								} else {
									if (b.settings.extend.settings.supportGridEditTriggerEvent == "rowDoubleClick") {
										a(this)
												.dblclick(
														function() {
															a(this)
																	.find(
																			".bsgrid_editgrid_hidden")
																	.each(
																			function() {
																				d(this)
																			})
														})
									} else {
										if (b.settings.extend.settings.supportGridEditTriggerEvent == "cellClick") {
											a(this)
													.find(
															".bsgrid_editgrid_hidden")
													.each(
															function() {
																var i = this;
																a(i)
																		.parent(
																				"td")
																		.click(
																				function() {
																					d(i)
																				})
															})
										} else {
											if (b.settings.extend.settings.supportGridEditTriggerEvent == "cellDoubleClick") {
												a(this)
														.find(
																".bsgrid_editgrid_hidden")
														.each(
																function() {
																	var i = this;
																	a(i)
																			.parent(
																					"td")
																			.dblclick(
																					function() {
																						d(i)
																					})
																})
											}
										}
									}
								}
							}
						});
		function d(f) {
			var g = a(f).removeClass("bsgrid_editgrid_hidden").clone(true);
			a(f).parent("td").html(g)
		}
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.afterRenderGridMethods,
			"addGridEditEvent",
			a.fn.bsgrid.extendAfterRenderGrid.addGridEditEvent);
	a.fn.bsgrid.extendAfterRenderGrid.aggregation = function(f, c, b) {
		var e = a.fn.bsgrid.getGridObj(b.gridId);
		var d = b.columnsModel;
		a(
				"#" + b.gridId + " tfoot tr td["
						+ b.settings.colsProperties.aggAttr + "!='']")
				.each(
						function(g) {
							if (d[g].aggName != "") {
								var k = d[g].aggName;
								var j = null;
								if (k == "count") {
									j = b.curPageRowsNum
								} else {
									if (k == "countnotnone" || k == "sum"
											|| k == "avg" || k == "max"
											|| k == "min" || k == "concat") {
										if (k == "countnotnone") {
											j = 0
										}
										var h = new StringBuilder();
										a(
												"#" + b.gridId
														+ " tbody tr:lt("
														+ b.curPageRowsNum
														+ ")")
												.each(
														function(i) {
															var l = e
																	.getColumnValue(
																			i,
																			d[g].aggIndex);
															if (l == "") {
															} else {
																if (k == "countnotnone") {
																	j = (j == null ? 0
																			: j) + 1
																} else {
																	if (k == "sum"
																			|| k == "avg") {
																		if (!isNaN(l)) {
																			j = (j == null ? 0
																					: j)
																					+ parseFloat(l)
																		}
																	} else {
																		if (k == "max"
																				|| k == "min") {
																			if (!isNaN(l)
																					&& (j == null
																							|| (k == "max" && parseFloat(l) > j) || (k == "min" && parseFloat(l) < j))) {
																				j = parseFloat(l)
																			}
																		} else {
																			if (k == "concat") {
																				h
																						.append(l)
																			}
																		}
																	}
																}
															}
														});
										if (k == "avg" && j != null) {
											j = j / b.curPageRowsNum
										} else {
											if (k == "concat") {
												j = h.toString()
											}
										}
									}
								}
								j = j == null ? "" : j;
								a(this).html(j)
							}
						})
	};
	a.bsgrid.forcePushPropertyInObject(
			a.fn.bsgrid.defaults.extend.afterRenderGridMethods, "aggregation",
			a.fn.bsgrid.extendAfterRenderGrid.aggregation);
	a.fn.bsgrid.defaults.extend.getCheckedRowsIndexs = function(b) {
		var c = [];
		a("#" + b.gridId + " tbody tr").each(function(d) {
			if (a(this).find("td>input:checked").length == 1) {
				c[c.length] = d
			}
		});
		return c
	};
	a.fn.bsgrid.defaults.extend.getCheckedRowsRecords = function(c) {
		var b = [];
		a.each(a.fn.bsgrid.defaults.extend.getCheckedRowsIndexs(c), function(d,
				e) {
			b[b.length] = a.fn.bsgrid.getRecord(e, c)
		});
		return b
	};
	a.fn.bsgrid.defaults.extend.activeGridEditMode = function(b) {
		if (!b.settings.extend.settings.supportGridEdit) {
			return
		}
		a(
				"#" + b.gridId + " tbody tr:lt(" + b.curPageRowsNum
						+ ") td .bsgrid_editgrid_hidden").each(function() {
			var c = a(this).removeClass("bsgrid_editgrid_hidden").clone(true);
			a(this).parent("td").html(c)
		})
	};
	a.fn.bsgrid.defaults.extend.getChangedRowsIndexs = function(b) {
		var c = [];
		a("#" + b.gridId + " tbody tr").each(function(d) {
			var e = a.trim(a(this).data("change"));
			if (!isNaN(e) && parseInt(e) > 0) {
				c[c.length] = d
			}
		});
		return c
	};
	a.fn.bsgrid.defaults.extend.getChangedRowsOldRecords = function(c) {
		var b = [];
		a.each(a.fn.bsgrid.defaults.extend.getChangedRowsIndexs(c), function(d,
				e) {
			b[b.length] = a.fn.bsgrid.getRecord(e, c)
		});
		return b
	};
	a.fn.bsgrid.defaults.extend.getRowsChangedColumnsValue = function(c) {
		var b = {};
		a
				.each(
						a.fn.bsgrid.defaults.extend.getChangedRowsIndexs(c),
						function(d, e) {
							b["row_" + e] = {};
							a("#" + c.gridId + " tbody tr:eq(" + e + ") td")
									.each(
											function(f) {
												if (a(this)
														.find(
																".bsgrid_editgrid_change").length > 0) {
													b["row_" + e][c.columnsModel[f].index] = a(
															this)
															.find(
																	".bsgrid_editgrid_change")
															.val()
												}
											})
						});
		return b
	}
})(jQuery);
(function(a) {
	a.bsgrid_export = {
		defaults : {
			url : "",
			exportFileName : "export",
			colsProperties : {
				width : 100,
				align : "center",
				exportAttr : "w_export",
				indexAttr : "w_index",
				widthAttr : "width",
				alignAttr : "w_align"
			},
			colWidthPercentmultiplier : 14,
			requestParamsName : {
				exportFileName : "exportFileName",
				colNames : "dataNames",
				colIndexs : "dataIndexs",
				colWidths : "dataLengths",
				colAligns : "dataAligns"
			}
		},
		doExport : function(g, l, c) {
			if (l == undefined) {
				l = {}
			}
			var b = {};
			if (c == undefined) {
				c = {}
			}
			a.extend(true, b, a.bsgrid_export.defaults, c);
			var n = "", f = "", m = "", h = "";
			for (var e = 0; e < g.length; e++) {
				if (a.trim(g.eq(e).attr(b.colsProperties.exportAttr)) != "false") {
					n = n + "," + a.trim(g.eq(e).text());
					f = f + ","
							+ a.trim(g.eq(e).attr(b.colsProperties.indexAttr));
					var k = a.trim(g.eq(e).attr(b.colsProperties.widthAttr))
							.toLocaleLowerCase();
					var d = b.colsProperties.width;
					if (isNaN(k)) {
						if (k.endWith("px")) {
							d = parseInt(k.replace("px", ""))
						} else {
							if (k.endWith("%")) {
								k = k.replace("%", "");
								if (!isNaN(k)) {
									d = b.colWidthPercentmultiplier
											* parseInt(k)
								}
							}
						}
					}
					m = m + "," + d;
					var j = a.trim(g.eq(e).attr(b.colsProperties.alignAttr));
					if (j == "") {
						j = b.colsProperties.align
					}
					h = h + "," + j
				}
			}
			document.location.href = b.url
					+ (b.url.indexOf("?") < 0 ? "?" : "&")
					+ b.requestParamsName.exportFileName + "="
					+ encodeURIComponent(encodeURIComponent(b.exportFileName))
					+ "&" + b.requestParamsName.colNames + "="
					+ encodeURIComponent(encodeURIComponent(n.substring(1)))
					+ "&" + b.requestParamsName.colIndexs + "="
					+ f.substring(1) + "&" + b.requestParamsName.colWidths
					+ "=" + m.substring(1) + "&"
					+ b.requestParamsName.colAligns + "=" + h.substring(1)
					+ (l.length == 0 ? "" : ("&" + a.bsgrid.param(l, true)))
		}
	}
})(jQuery);
(function(a) {
	a.fn.bsgrid_form = {
		defaults : {},
		formObjs : {},
		init : function(e, d) {
			var c = {
				settings : a.extend(true, {}, a.fn.bsgrid_form.defaults, d),
				formId : e,
				jqueryObj : a("#" + e),
				formType : ""
			};
			var b = {
				options : c,
				addAssistShowFormTags : function() {
					a.fn.bsgrid_form.addAssistShowFormTags(c)
				},
				showForm : function(f) {
					a.fn.bsgrid_form.showForm(c, f)
				},
				showOrHideRequireSpan : function(f) {
					a.fn.bsgrid_form.showOrHideRequireSpan(c, f)
				},
				showOrHideAssistForms : function(f) {
					a.fn.bsgrid_form.showOrHideAssistForms(c, f)
				},
				showOrHideTag : function(f) {
					a.fn.bsgrid_form.showOrHideTag(c, f)
				}
			};
			a.fn.bsgrid_form.formObjs[e] = b;
			b.addAssistShowFormTags();
			return b
		},
		getFormObj : function(c) {
			var b = a.fn.bsgrid_form.formObjs[c];
			return b ? b : null
		},
		addAssistShowFormTags : function(b) {
			a(".formInput select", b.jqueryObj)
					.each(
							function() {
								a(this)
										.before(
												'<input type="text" style="display: none;" />');
								var d = a(this).get(0).attributes;
								for (var e = 0; e < d.length; e++) {
									var c = d[e].name;
									if (c.toLowerCase().endWith("able")
											&& a(this).attr(c) == "false") {
										a(this).prev("input").attr(c, "false")
									}
								}
							});
			a(".formInput textarea", b.jqueryObj)
					.each(
							function() {
								a(this)
										.before(
												'<div class="assist" style="display: none;"></div>')
							})
		},
		showForm : function(b, c) {
			b.formType = c;
			this.showOrHideRequireSpan(b, c);
			this.showOrHideAssistForms(b, c);
			this.showOrHideTag(b, c);
			if (c.startWith("view")) {
				a(".formInput :input:not(:button,:submit,:reset)", b.jqueryObj)
						.css({
							"border-width" : "0"
						}).attr("readOnly", "readOnly")
			} else {
				if (c.startWith("add")) {
					a(".formInput :input:not(:button,:submit,:reset)",
							b.jqueryObj).css({
						border : "solid 1px #abadb3"
					}).removeAttr("readOnly")
				} else {
					if (c.startWith("edit")) {
						a(".formInput :input:not(:button,:submit,:reset)",
								b.jqueryObj).css({
							border : "solid 1px #abadb3"
						}).removeAttr("readOnly");
						a(".formInput :input[" + c + "Able=false]", b.jqueryObj)
								.css({
									"border-width" : "0"
								}).attr("readOnly", "readOnly")
					}
				}
			}
		},
		showOrHideRequireSpan : function(b, c) {
			if (c.startWith("view")) {
				a(".formLabel span.require", b.jqueryObj).hide()
			} else {
				if (c.startWith("edit")) {
					a(
							".formLabel:has(span.require) ~ .formInput:has(:input["
									+ c + "Able=false])", b.jqueryObj).prev()
							.find("span.require").hide()
				} else {
					a(".formLabel span.require", b.jqueryObj).show()
				}
			}
		},
		showOrHideAssistForms : function(b, c) {
			a(".formInput select", b.jqueryObj)
					.each(
							function() {
								var e = (c.startWith("view") || (c
										.startWith("edit") && a(this).attr(
										c + "Able") == "false")) ? "block"
										: "none";
								a(this).prev("input").css("display", e).val(
										a(this).find("option:selected").text());
								var d = e == "block" ? "none" : "block";
								a(this).css("display", d)
							});
			a(".formInput textarea", b.jqueryObj)
					.each(
							function() {
								var e = (c.startWith("view") || (c
										.startWith("edit") && a(this).attr(
										c + "Able") == "false")) ? "block"
										: "none";
								a(this).prev("div").css("display", e).html(
										a(this).val());
								var d = e == "block" ? "none" : "block";
								a(this).css("display", d)
							})
		},
		showOrHideTag : function(b, c) {
			a("*", b.jqueryObj)
					.each(
							function() {
								var d = a.trim(a(this).attr("showType"));
								if (d != "") {
									if ((c.startWith("view")
											|| c.startWith("add") || c
											.startWith("edit"))
											&& ("," + d + ",").indexOf("," + c
													+ ",") > -1) {
										a(this).show()
									} else {
										a(this).hide()
									}
								}
							})
		}
	}
})(jQuery);
