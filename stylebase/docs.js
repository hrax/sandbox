window.addEvent("domready", function() {
			// show example source
			$$(".docs-example").each(function(item) {
				var t = 0, i = 0, html = item.get("html");
				
				// count tabs on the beginning
				if (html.charAt(0) == "\n") i++;
				while (html.charAt(i) == "\t") {
					t++;
					i++;
				}
				
				// insert source and cleanup
				new Element("pre", {
					"class": "docs-source pre-scrollable", 
					"html": item.get("html")
						.replace(/^\n/, "")
						.replace(/\n$/, "")
						.replace(new RegExp("\t{"+t+"}", "g"), "")
						.replace(new RegExp("\n\t{"+(t-1)+"}$"), "")
						.replace(/&/g, "&amp;")
						.replace(/</g, "&lt;")
						.replace(/>/g, "&gt;")
						.replace(/\t/g, "  ")
						.replace(/\s+\n/g, "\n")
				}).inject(item, "after");
				item.addClass("docs-example-source");
			});
			
			// create TOC
			var body = $$("body")[0];
			var toc = new Element("div", {"class": "docs-toc", "id": "docs-toc"});
			body.adopt(toc);
			
			var list = new Element("ul", {"class": "list-unstyled"});
			$$(".docs header h1").each(function(item) {
					var caption = item.get("text");
					var id = item.get("id") || caption.replace(/[^a-z0-9]/gi, "-").toLowerCase();
					
					item.set("id", id);
					var li = new Element("li", {"id": id + "-toc"})
						.adopt(new Element("a", {"href": "#" + id, "data-id" : id, "text": caption}))
						.inject(list);
						
						var children = item.getParent(".docs").getChildren(">h1");
						var empty = children == null || children.length == 0;
						
						if (!empty) {
							var ch = new Element("ul").inject(li);
							children.each(function(child) {
									caption = child.get("text");
									id = child.get("id") || caption.replace(/[^a-z0-9]/gi, "-").toLowerCase();
									
									child.set("id", id);
									
									new Element("li", {"id": id + "-toc"})
										.adopt(new Element("a", {"href": "#" + id, "data-id" : id, "text": caption}))
										.inject(ch);
							}, ch);
						}
						
			});
			
			if (list.getChildren().length != 0) {
				list.inject(toc);
			}
			
			if (toc.getChildren().length == 0) {
				toc.destroy();
			}
			
			var scrollspy = new moostrapScrollspy($$('#docs-toc ul')[0], {offset: -40});
			toc.adopt(new Element("a", {
						"href": "#",
						"html": "&times;",
						events: {
							"click": function(e) {
								e.stop();
								toc.toggleClass("off");
								scrollspy.activate(scrollspy.indexOf(e.target));
							}
						},
						"title": "Toggle Table of Contents" 
				}));
		});
