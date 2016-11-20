var index = 0;
		function addPanel(url,title){
			index++;

			 if (!$("#tabs").tabs('exists', title)) {
			        var allTabs = $("#tabs").tabs("tabs");
			        $("#tabs").tabs('add', {
			            title: title,
			            content: '<iframe src="' + url + '" frameborder=0 height=100% width=100% scrolling=no></iframe>',
			            closable: true
			         });
			    } else {  
			        $("#tabs").tabs('select', title);
			    }

			/*$('#tt').tabs('add',{
				title: tab,
				content: '<iframe src="' + url + '" frameborder=0 height=100% width=100% scrolling=no></iframe>',
				//href:link,
				closable: true
				
			});	*/
		}
		function removePanel(){
			var tab = $('#tt').tabs('getSelected');
			if (tab){
				var index = $('#tt').tabs('getTabIndex', tab);
				$('#tt').tabs('close', index);
			}
		}