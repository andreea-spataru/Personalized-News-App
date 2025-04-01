from flask import Flask, jsonify, request

app = Flask(__name__)

# Știri în limba română
news_data_ro = {
    "technology": [
        {"title": "Google lansează Gemini AI", "content": "O nouă eră a inteligenței artificiale începe..."},
        {"title": "Tesla anunță Model Z", "content": "Tesla prezintă un nou model complet autonom..."},
        {"title": "Meta dezvoltă ochelari AR", "content": "Meta lucrează la o nouă generație de ochelari inteligenți..."},
        {"title": "Apple lansează iPhone 16", "content": "iPhone 16 vine cu funcții AI integrate..."},
        {"title": "Samsung inovează în ecrane", "content": "Ecranele pliabile devin tot mai populare..."}
    ],
    "sports": [
        {"title": "Echipa Steaua câștigă campionatul", "content": "O finală tensionată decisă la penalty-uri..."},
        {"title": "Simona Halep revine în top 10", "content": "După o accidentare lungă, Simona revine în forță..."},
        {"title": "Naționala U21 învinge Franța", "content": "Tinerii tricolori au făcut un meci excelent..."},
        {"title": "Cristiano Ronaldo semnează cu un nou club", "content": "Transferul anului în fotbalul european..."},
        {"title": "România câștigă la handbal", "content": "Un meci spectaculos împotriva Spaniei..."}
    ],
    "business": [
        {"title": "Bursa din București crește", "content": "Indicele BET a atins un nou record..."},
        {"title": "Banca Centrală scade dobânda", "content": "Decizie importantă pentru economie..."},
        {"title": "Startup românesc primește finanțare", "content": "O investiție de 2 milioane de euro..."},
        {"title": "România atrage investitori străini", "content": "Forumul economic de la Cluj a atras atenția..."}
    ],
    "health": [
        {"title": "Vaccinul antigripal disponibil", "content": "Campania națională a început..."},
        {"title": "Crește numărul cazurilor de viroză", "content": "Medicii recomandă precauție..."},
        {"title": "Alimentația sănătoasă devine trend", "content": "Tot mai mulți români adoptă diete echilibrate..."}
    ]
}

# Știri în limba engleză
news_data_en = {
    "technology": [
        {"title": "Google launches Gemini AI", "content": "A new era of artificial intelligence begins..."},
        {"title": "Tesla reveals Model Z", "content": "The new autonomous vehicle from Tesla impresses..."},
        {"title": "Meta develops AR glasses", "content": "Meta's smart glasses could change daily life..."},
        {"title": "Apple introduces iPhone 16", "content": "The latest iPhone comes with AI-powered features..."},
        {"title": "Samsung innovates foldable displays", "content": "Foldables are the future of smartphones..."}
    ],
    "sports": [
        {"title": "Steaua wins the championship", "content": "A dramatic final decided by penalties..."},
        {"title": "Simona Halep returns to top 10", "content": "Comeback after a long injury break..."},
        {"title": "Romania U21 beats France", "content": "Young team shines in key match..."},
        {"title": "Cristiano Ronaldo joins new club", "content": "Biggest transfer in European football..."},
        {"title": "Romania wins handball match", "content": "Thrilling game against Spain ends in victory..."}
    ],
    "business": [
        {"title": "Bucharest Stock Exchange hits record", "content": "BET index climbs to new highs..."},
        {"title": "Central Bank cuts interest rate", "content": "Decision could stimulate economic growth..."},
        {"title": "Romanian startup gets funded", "content": "2M euro investment sparks innovation..."},
        {"title": "Foreign investors eye Romania", "content": "Economic forum in Cluj gathers attention..."}
    ],
    "health": [
        {"title": "Flu vaccine now available", "content": "National campaign officially launched..."},
        {"title": "Rise in seasonal viruses", "content": "Doctors recommend caution and rest..."},
        {"title": "Healthy food becomes a trend", "content": "More people adopting balanced diets..."}
    ]
}

@app.route('/news', methods=['GET'])
def get_news():
    category = request.args.get('category', 'technology')
    lang = request.args.get('lang', 'en')

    if lang == "ro":
        return jsonify(news_data_ro.get(category, []))
    else:
        return jsonify(news_data_en.get(category, []))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
