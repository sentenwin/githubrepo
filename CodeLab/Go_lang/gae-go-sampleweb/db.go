package helloappengine

// show import OMIT
import (
	"appengine"
	"appengine/datastore"
// end show import OMIT
	"html/template"
	"net/http"
	"time"
)

// show type OMIT
type Info struct {
	Data string
	Time time.Time
}
// end show type OMIT

func init() {
	http.HandleFunc("/", onRoot)
	http.HandleFunc("/add", onAdd)
}

func onRoot(w http.ResponseWriter, r *http.Request) {
	t, _ := template.New("root").Parse(tmplStr)

	t.ExecuteTemplate(w, "root", nil)
}

func onAdd(w http.ResponseWriter, r *http.Request) {
	c := appengine.NewContext(r)
	s := r.FormValue("info")
	addInfo(c, s)

	onRoot(w, r)
}

func addInfo(c appengine.Context, s string) {
// show put OMIT
	key := datastore.NewIncompleteKey(c, "Info", nil)
	newInfo := Info{
		s,
		time.Now(),
	}
	datastore.Put(c, key, &newInfo)
// end show put OMIT
}

var tmplStr = `
{{define "root"}}
<html>
<body>
	<h3>Enter new info:</h3>
	<form action="/add">
		<input name="info" type="text">
		<input type="submit">
	</form>
</body>
</html>
{{end}}
`
