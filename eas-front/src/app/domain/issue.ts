export interface Issue {
  number: string,
  doi: string,
  publishingDate: string,
  pdfMeta: string,
  coverMeta: string,
  articles: Article[]
}

export interface Article {
  title: string,
  author: string,
  authorAbout: string,
  citation: string,
  annotation: string,
  rubric: string,
  keywords: string[],
  pages: ArticlePages,
  pdfMeta: string
}

export interface ArticlePages {
  pageStart: number,
  pageEnd: number
}
