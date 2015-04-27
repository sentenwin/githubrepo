jQuery(document).ready(function(){
	"use strict";
	
	jQuery(document).foundation();

	jQuery(".event-info ul").addClass("large-block-grid-" + jQuery(".event-info ul li").length); 

	
	var wplook = {

		// Start Functions
		startWpl: function () {
			wplook.ParallaxBackgroundsWpl();
			wplook.MobileMenuWpl();
			wplook.AnimateCssWpl();
			wplook.OwlSliderWpl();
			wplook.MainMenuWpl();
			wplook.DetectiOSWpl();
		},

		// Parallax Backgrounds
		ParallaxBackgroundsWpl: function () {
			jQuery(window).stellar({
				responsive: true,
				horizontalScrolling: false,
				verticalScrolling: true,
				parallaxBackgrounds: true,
				parallaxElements: true,
				hideDistantElements: true
			});
		},


		// Mobile Menu
		MobileMenuWpl: function () {
			jQuery( ".navmobile a" ).click(function(e) {
				e.preventDefault();
				jQuery(this).toggleClass('close');
				jQuery(".menu").toggleClass('showme');
			});
			jQuery(".menu li a").click(function(e) {
				jQuery(this).toggleClass('close');
				jQuery(".menu").toggleClass('showme');
			});
			jQuery(".navmobile").waypoint(function(direction) {
				jQuery(".navbar-fixed").toggleClass("isStuck");
			}, {offset: -1});

			if (jQuery(window).width() < 950) {
				jQuery(".main_menu.navbar-fixed").css("position", "relative");
			}
		},


		// Animate CSS and JS
		AnimateCssWpl: function () {
			//Animate titles
			var aTitle = [".speakers", ".WPlookAnounce", ".pagecontent", ".schedule", ".partners", ".buytickets", ".testimonials", ".widget_latestposts_homepage" ];
			function animateTitle (val) {
				jQuery(val +' h2').waypoint(function(direction) {
					jQuery(val +' h2').toggleClass("animate_title");
				}, { offset: 300 });
			}
			for (var i = aTitle.length - 1; i >= 0; i--) {
				animateTitle(aTitle[i]);
			}

			//Animate Buy Tickets boxes
			jQuery( ".buytickets_item" ).each(function( index ) {
				jQuery( this ).waypoint(function(direction) {
					jQuery( this ).toggleClass("animateBuyTicketsBoxes-"+index);
				}, { offset: 550 });
			});

			//Animate latest post home page
			jQuery( ".widget_latestposts_homepage li" ).each(function( index ) {
				jQuery( this ).waypoint(function(direction) {
					jQuery( this ).toggleClass("widget_latestpost_homepage-"+index);
				}, { offset: 400 });
			});
		},

		// 
		OwlSliderWpl: function () {
			//Slider owl-testimonials
			var owltestimonials = jQuery("#owl-testimonials");
			owltestimonials.owlCarousel({
				items : 3, 
				itemsDesktop : [1000,3],
				itemsDesktopSmall : [900,2],
				itemsTablet: [600,1],
				itemsMobile : [480,1]
			});

			// Navigation owl-testimonials
			jQuery(".next").click(function(){
				owltestimonials.trigger('owl.next');
			});
			jQuery(".prev").click(function(){
				owltestimonials.trigger('owl.prev');
			});

						// Owl-gallery Single Page
			var owlsingle = jQuery("#owl-gallery-single");
				owlsingle.owlCarousel({
				singleItem:true
			});
			jQuery(".customNavigation-single .next-gallery").click(function(){
				owlsingle.trigger('owl.next');
			});
			jQuery(".customNavigation-single .prev-gallery").click(function(){
				owlsingle.trigger('owl.prev');
			});

			// Owl-gallery Widget
			var owlgallery = jQuery("#owl-gallery");
				owlgallery.owlCarousel({
				singleItem:true
			});
			jQuery(".customNavigation .next-gallery").click(function(){
				owlgallery.trigger('owl.next');
			});
			jQuery(".customNavigation .prev-gallery").click(function(){
				owlgallery.trigger('owl.prev');
			});



		},

		// Main menu
		MainMenuWpl: function () {
			jQuery('.menu').onePageNav({
				currentClass: 'active',
				changeHash: false,
				scrollSpeed: 750,
				scrollThreshold: 0.5,
				filter: '',
				easing: 'swing'
			});
		},
		// iosDetect
		DetectiOSWpl: function () {
			Modernizr.addTest('ipad', function () {
				return !!navigator.userAgent.match(/iPad/i);
			});
			Modernizr.addTest('iphone', function () {
				return !!navigator.userAgent.match(/iPhone/i);
			});
			Modernizr.addTest('ipod', function () {
				return !!navigator.userAgent.match(/iPod/i);
			});
			Modernizr.addTest('appleios', function () {
				return (Modernizr.ipad || Modernizr.ipod || Modernizr.iphone);
			});
		},


	};

	jQuery(document).ready(function () {
		wplook.startWpl();
	});

});

// Share buttons
function twwindows(object) {
	window.open( object, "twshare", "height=400,width=550,resizable=1,toolbar=0,menubar=0,status=0,location=0" ) 
}

function fbwindows(object) {
	window.open( object, "fbshare", "height=380,width=660,resizable=0,toolbar=0,menubar=0,status=0,location=0,scrollbars=0" ) 
}

function pinwindows(object) {
	window.open( object, "pinshare", "height=270,width=630,resizable=0,toolbar=0,menubar=0,status=0,location=0,scrollbars=0" )
}