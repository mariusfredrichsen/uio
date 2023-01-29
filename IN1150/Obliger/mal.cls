% Mal for innleveringer i IN1150 -- Logiske metoder
\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{innlevering}[2023/01/26 Mal for innleveringer i IN1150 -- Logiske metoder]

% Vi bruker article-klassen
\LoadClass[12pt]{article}

% Vi vil ha en A4-side med 25 mm marger
\RequirePackage[
a4paper,
top=25mm,
headheight=10mm,
bottom=25mm,
left=20mm,
right=20mm,
%showframe
]{geometry}

% Info
\newcommand{\brukernavn}[1]{\newcommand\@brukernavn{#1}}
\newcommand{\tilbakemelding}[1]{\newcommand\@tilbakemelding{#1}}
\let\@brukernavn\ok

% Pakker for norsk
\RequirePackage[utf8]{inputenc}
\RequirePackage[T1]{fontenc}
\RequirePackage[norsk]{babel}

% Fonter
\RequirePackage{tgpagella}
\RequirePackage[small,euler-digits]{eulervm}

% Ikke indentering + ok mellomrom for ny paragrafer
\AtBeginDocument{\setlength{\parindent}{0pt}\setlength{\parskip}{12pt}}

% Tikz for tegning og farger
\RequirePackage{tikz}

% Farger
\definecolor{okgray}{RGB}{119,119,119}
\definecolor{okbluesource}{RGB}{51,102,170}
\colorlet{okblue}{okbluesource!90!blue!90!black}
\definecolor{okredsource}{RGB}{238,51,51}
\colorlet{okred}{okredsource!80!black}
\definecolor{okgreensource}{RGB}{102,170,85}
\colorlet{okgreen}{okgreensource!90!black}

% Vi legger til en header med brukernavn og tilbakemelding samt dato i footer
\RequirePackage{fancyhdr}
\RequirePackage{ifthen}
\RequirePackage{datetime}
\newdateformat{mydate}{\THEYEAR-\twodigit{\THEMONTH}-\twodigit{\THEDAY}}
\fancypagestyle{in1150-innlevering}{
  \fancyhead[R]{
    {\small\ttfamily Innlevering for~\ifthenelse{\equal{\@tilbakemelding}{}}{\textcolor{okred}{ubesvart}}{\textcolor{okblue}{\@brukernavn}}}\\
    {\small\ttfamily Ønsker tilbakemelding?~\ifthenelse{\equal{\@tilbakemelding}{}}{\textcolor{okred}{ubesvart}}{\textcolor{okblue}{\@tilbakemelding}}}}
  \fancyfoot[L]{}
  \fancyfoot[C]{}
  \fancyfoot[R]{\tiny\ttfamily\textcolor{okgray}{\mydate\today\ \currenttime}}
  \renewcommand{\headrulewidth}{0pt}
  \renewcommand{\footrulewidth}{0pt}
}
\pagestyle{in1150-innlevering}

% Progressbar
\def\progressbar#1{
  \begin{tikzpicture}
    \foreach \i/\x in {1/0,2/1,3/1,4/0,5/1,6/0,7/0,8/1,9/1,10/0,11/0,12/1,13/0,14/1,15/1,16/0,17/1,18/0,19/0,20/1,21/0,22/1,23/1,24/0,25/0,26/1,27/1,28/0,29/1,30/0,31/0,32/1,33/1,34/0,35/0,36/1,37/0,38/1,39/1,40/0,41/0,42/1,43/1,44/0,45/1,46/0,47/0,48/1,49/0,50/1,51/1,52/0,53/1,54/0,55/0,56/1,57/1,58/0,59/0,60/1,61/0,62/1,63/1,64/0,65/1,66/0,67/0,68/1,69/0,70/1,71/1,72/0,73/0,74/1,75/1,76/0,77/1,78/0,79/0,80/1,81/0,82/1,83/1,84/0,85/1,86/0,87/0,88/1,89/1,90/0,91/0,92/1,93/0,94/1,95/1,96/0,97/0,98/1,99/1,100/0,101/1,102/0,103/0,104/1,105/1,106/0,107/0,108/1,109/0,110/1,111/1,112/0,113/1,114/0,115/0,116/1,117/0,118/1,119/1,120/0,121/0,122/1,123/1,124/0,125/1,126/0,127/0,128/1}{
        \pgfmathparse{\x ? "black!40" : "okblue!40"}
        \edef\farge{\pgfmathresult}
        \node [inner sep=0,outer sep=0,rectangle,fill=\farge,minimum size=3pt]
        (node\i) at (3.78pt*\i,0) {};
      }
      \ifthenelse{\equal{#1}{0}}{}{\draw [black, thin] (node#1.south west) rectangle (node#1.north east);}
    \end{tikzpicture}}

% Kapittelfunksjon
\newcommand{\kapittel}[1]{
  \setcounter{kapittelcounter}{#1}
  {\Large Oppgaver til kapittel #1}
  \vskip-2.5ex
  \progressbar{#1}
  \vskip1ex}

% Nummerering av oppgaver
\RequirePackage{chngcntr}
\newcounter{kapittelcounter}
\newcounter{oppgavecounter}
\counterwithin{oppgavecounter}{kapittelcounter}

% Matematikk-støtte og oppgavemiljø
\RequirePackage{amsmath,amssymb}
\RequirePackage[amsmath,thmmarks,hyperref]{ntheorem}
\newtheoremstyle{leftmarginstyle}{\item[\theorem@headerfont\llap{##2\theorem@separator\enspace}]}{}
\theoremheaderfont{\bfseries\color{black}}
\theorembodyfont{\normalfont}
\theoremsymbol{}
\theoremseparator{}
\theoremstyle{leftmarginstyle}
\newtheorem{oppgavewrapper}[oppgavecounter]{}
\newenvironment{oppgave}[2][\thekapittelcounter]{\setcounter{kapittelcounter}{#1}\setcounter{oppgavecounter}{\number\numexpr#2-1\relax}\begin{oppgavewrapper}}{\end{oppgavewrapper}}

% Deloppgavemiljø
\RequirePackage{enumitem}
\SetLabelAlign{nr}{\strut\smash{\textcolor{black!75}{(\makebox[0.5em]{\hfil#1\hfil})}}}
\newlist{deloppgaver}{enumerate}{1}
\setlist[deloppgaver]{itemsep=1pt,parsep=1pt,leftmargin=1.9em, itemindent=*, align=nr, label=\alph*}

% Noen nyttige forkortelser
\newcommand{\set}[1]{\ensuremath{\{#1\}}}
\newcommand{\tuple}[1]{\ensuremath{\langle #1 \rangle}}
\newcommand{\imp}{\ensuremath{\rightarrow}}
\newcommand{\union}{\ensuremath{\cup}}
\newcommand{\snitt}{\ensuremath{\cap}}
\newcommand{\str}[1]{\textcolor{blue}{\normalfont\texttt{#1}}}

% Local variables:
% TeX-master: "main.tex"
% End: