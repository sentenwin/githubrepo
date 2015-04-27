var map;
var montreal = new google.maps.LatLng(wplMap.Lat, wplMap.Long);

var MY_MAPTYPE_ID = 'custom_style';
function initialize() {
	var featureOpts = [
		{
			stylers: [
				{ saturation: -20 },
				{ lightness: 40 },
				{ visibility: 'simplified' },
				{ gamma: 0.8 },
				{ weight: 0.4 }
			]
		},
		{
			elementType: 'labels',
			stylers: [
				{ visibility: 'on' }
			]
		},
		{
			featureType: 'water',
			stylers: [
				{ color: '#dee8ff' }
			]
		}
	];

	var mapOptions = {
		zoom: 14,
		scrollwheel: false,
		panControl: false,
		mapTypeControl: false,
			streetViewControl: false,
		center: new google.maps.LatLng(wplMap.Lat, wplMap.Long),
		mapTypeControlOptions: {
			mapTypeIds: [google.maps.MapTypeId.ROADMAP, MY_MAPTYPE_ID]
		},
		mapTypeId: MY_MAPTYPE_ID
	};

	map = new google.maps.Map(document.getElementById('js-map'),mapOptions);
	
	var myLatLng = new google.maps.LatLng(wplMarker.Lat, wplMarker.Long);
	var beachMarker = new google.maps.Marker({
		position: myLatLng,
		map: map,
		icon: image
	});
	var styledMapOptions = {
		name: 'Custom Style'
	};

	var customMapType = new google.maps.StyledMapType(featureOpts, styledMapOptions);

	map.mapTypes.set(MY_MAPTYPE_ID, customMapType);
}

google.maps.event.addDomListener(window, 'load', initialize);