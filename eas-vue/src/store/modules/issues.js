
export default{
    actions:{
        async fetchIssues(context){
            const resp = await fetch('http://localhost:20000/eas/issue',{
                method: "GET",
                headers: { "Accept": "application/json" }
            });

            const issues = await resp.json();
            context.commit('fetchAllIssues', issues);
        },
        // eslint-disable-next-line no-unused-vars
        async fetchDeleteIssue({commit}, id){
          const resp = await fetch('http://localhost:20000/eas/issue' + id,{
              method: "DELETE"
          });
          const answer  = await resp.json();
          console.log(answer);

        }
    },
    mutations:{
        fetchAllIssues(state, issues) {
            state.allIssues = issues.data;
            state.returningIssues = issues.data;
            let articleList = [];
            state.allIssues.forEach(i => {
              i.articles.forEach(a => a.issueId = i.number)
              articleList.push(...i.articles);
            state.allArticles = articleList;
          });
        },
        getAllArticlesById(state, id){
           state.articles = state.allIssues.filter(issue => issue.number == id)[0].articles;
        },
        getIssuesForCarousel(state){
            state.returningIssues = state.allIssues.sort(function(a,b){
                return new Date(b.date) - new Date(a.date);
              }).slice(0,4);

              console.log(state.returningIssues)
             
        },
        GetAllArticles(state){      
         
          let articleList = [];
           state.allIssues.forEach(i => {
             i.articles.forEach(a => a.issueId = i.number)
             articleList.push(...i.articles)});
           state.allArticles = articleList;
       },
       GetArticlesBySearchString(state, searchString){
          let articleList = [];
          state.allIssues.forEach(i => {
            i.articles.forEach(a => a.issueId = i.number)
            articleList.push(...i.articles)});
          state.allArticles = articleList;
          console.log(searchString)
          state.allArticles = state.allArticles.filter(article => article.title.toLowerCase().match(searchString.toLowerCase()) 
                                                                 || article.keywords[0].toLowerCase() == searchString.toLowerCase() 
                                                                 || article.keywords[1].toLowerCase() == searchString.toLowerCase());
       },

       GetIssuesForCatalog(state){
           state.returningIssues = state.allIssues;
       }
    },
    state:{
        articles:[],
        allArticles:[],
        allIssues:[],
        returningIssues:[],
        fakeIssues:[
            {
                number: "1",
                doi: "doi/com",
                publishingDate: "2020-12-21",
                pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf",
                coverMeta: "https://media.kpfu.ru/sites/default/files/2018-10/%D0%9A%D0%A4%D0%A3%20%D1%84%D0%BE%D1%82%D0%BE%D1%81%D1%82%D0%BE%D0%BA.jpg",
                articles: [
                  {
                    title: "Article 1 Title",
                    author: "Article 1 Author",
                    authorAbout: "Article 1 Author about",
                    citation: "Article 1 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["Cheese", "Bread"],
                    pages: {
                      pageStart: 0,
                      pageEnd: 50
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 2 Title",
                    author: "Article 2 Author",
                    authorAbout: "Article 2 Author about",
                    citation: "Article 2 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["Milk", "Soda"],
                    pages: {
                      pageStart: 51,
                      pageEnd: 100
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 3 Title",
                    author: "Article 3 Author",
                    authorAbout: "Article 3 Author about",
                    citation: "Article 3 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["Sugar", "Water"],
                    pages: {
                      pageStart: 101,
                      pageEnd: 151
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 4 Title",
                    author: "Article 4 Author",
                    authorAbout: "Article 4 Author about",
                    citation: "Article 4 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["Cream", "Sosiski"],
                    pages: {
                      pageStart: 152,
                      pageEnd: 200
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  }
                ]
              },
              {
                number: "2",
                doi: "doi/com",
                publishingDate: "2020-12-21",
                pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf",
                coverMeta: "https://realnoevremya.ru/uploads/articles/7d/c0/b2b55b8e0fed4bfc.jpg",
                articles: [
                  {
                    title: "Article 1 Title",
                    author: "Article 1 Author",
                    authorAbout: "Article 1 Author about",
                    citation: "Article 1 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 0,
                      pageEnd: 50
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 2 Title",
                    author: "Article 2 Author",
                    authorAbout: "Article 2 Author about",
                    citation: "Article 2 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 51,
                      pageEnd: 100
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 3 Title",
                    author: "Article 3 Author",
                    authorAbout: "Article 3 Author about",
                    citation: "Article 3 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 101,
                      pageEnd: 151
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 4 Title",
                    author: "Article 4 Author",
                    authorAbout: "Article 4 Author about",
                    citation: "Article 4 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 152,
                      pageEnd: 200
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  }
                ]
              },
              {
                number: "3",
                doi: "doi/com",
                publishingDate: "2020-12-21",
                pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf",
                coverMeta: "https://kpfu.ru/portal/docs/F1444449617/jNtUnO4yMig.jpg",
                articles: [
                  {
                    title: "Article 1 Title",
                    author: "Article 1 Author",
                    authorAbout: "Article 1 Author about",
                    citation: "Article 1 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 0,
                      pageEnd: 50
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 2 Title",
                    author: "Article 2 Author",
                    authorAbout: "Article 2 Author about",
                    citation: "Article 2 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 51,
                      pageEnd: 100
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 3 Title",
                    author: "Article 3 Author",
                    authorAbout: "Article 3 Author about",
                    citation: "Article 3 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 101,
                      pageEnd: 151
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 4 Title",
                    author: "Article 4 Author",
                    authorAbout: "Article 4 Author about",
                    citation: "Article 4 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 152,
                      pageEnd: 200
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  }
                ]
              },
              {
                number: "4",
                doi: "doi/com",
                publishingDate: "2020-12-21",
                pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf",
                coverMeta: "https://kpfu.ru/portal/docs/F75491192/Q5bLTF7RPBo.jpg",
                articles: [
                  {
                    title: "Article 1 Title",
                    author: "Article 1 Author",
                    authorAbout: "Article 1 Author about",
                    citation: "Article 1 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 0,
                      pageEnd: 50
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 2 Title",
                    author: "Article 2 Author",
                    authorAbout: "Article 2 Author about",
                    citation: "Article 2 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "University life",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 51,
                      pageEnd: 100
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 3 Title",
                    author: "Article 3 Author",
                    authorAbout: "Article 3 Author about",
                    citation: "Article 3 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 101,
                      pageEnd: 151
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  },
                  {
                    title: "Article 4 Title",
                    author: "Article 4 Author",
                    authorAbout: "Article 4 Author about",
                    citation: "Article 4 Citation",
                    annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
                    rubric: "Somethisg about languages",
                    keywords: ["keyword 1", "keyword 2"],
                    pages: {
                      pageStart: 152,
                      pageEnd: 200
                    },
                    pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
                  }
                ]
              }        
        ],
    },
    getters:{
        getIssues(state) {
            return state.returningIssues;
        },
        getFakeIssues(state) {
            return state.fakeIssues;
        },
        getAllIssueArticles(state) {
            return state.articles;
        },
        getAllIssues(state) {
            return state.allIssues;
        },
        getAllArticles(state){
            return state.allArticles;
        }
    }
}