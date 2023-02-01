! function (t, e) {
  "function" == typeof define && define.amd ? define("calendar", ["jquery"], e) : "object" == typeof exports ? module.exports = e(require("jquery")) : e(t.jQuery)
}(this, function (t) {
  function e(t) {
    return "[object Date]" === V.call(t)
  }

  function a(t) {
    return "[object String]" === V.call(t)
  }

  function i(t) {
    return t.getAttribute("class") || t.getAttribute("className")
  }

  function n(e, a) {
    this.$element = t(e), this.options = t.extend({}, t.fn.calendar.defaults, a), this.$element.addClass("calendar " + this.options.customClass), this.width = this.options.width, this.height = this.options.height, this.date = this.options.date, this.selectedRang = this.options.selectedRang, this.data = this.options.data, this.init()
  }
  var s = {
      width: 280,
      height: 280,
      zIndex: 1,
      trigger: null,
      offset: [0, 1],
      customClass: "",
      view: "date",
      date: new Date,
      format: "yyyy/mm/dd",
      startWeek: 0,
      weekArray: ["日", "一", "二", "三", "四", "五", "六"],
      monthArray: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
      selectedRang: null,
      data: null,
      label: "{d}\n{v}",
      prev: "&lt;",
      next: "&gt;",
      viewChange: t.noop,
      onSelected: function (t, e, a) {},
      onMouseenter: t.noop,
      onClose: t.noop
    },
    o = "data-calendar-",
    r = "[" + o + "display-date]",
    h = "[" + o + "display-month]",
    l = "[" + o + "arrow-date]",
    d = "[" + o + "arrow-month]",
    c = o + "day",
    u = o + "month",
    p = "disabled",
    m = "markData",
    f = {
      date: "calendar-d",
      month: "calendar-m"
    },
    g = "old",
    v = "new",
    D = "now",
    w = "selected",
    y = '<i class="dot"></i>',
    M = '{year}/<span class="m">{month}</span>',
    $ = 'style="width:{w}px;height:{h}px;line-height:{h}px"',
    x = "<li " + $ + ">{wk}</li>",
    b = "<li " + $ + ' class="{class}" {action}="{date}">{value}</li>',
    I = "<li " + $ + " " + u + ">{m}</li>",
    k = ['<div class="calendar-inner">', '<div class="calendar-views">', '<div class="view view-date">', '<div class="calendar-hd">', '<a href="javascript:;" data-calendar-display-date class="calendar-display">', '{yyyy}/<span class="m">{mm}</span>', "</a>", '<div class="calendar-arrow">', '<span class="prev" data-calendar-arrow-date>{prev}</span>', '<span class="next" data-calendar-arrow-date>{next}</span>', "</div>", "</div>", '<div class="calendar-ct">', '<ol class="week">{week}</ol>', '<ul class="date-items"></ul>', "</div>", "</div>", '<div class="view view-month">', '<div class="calendar-hd">', '<a href="javascript:;" data-calendar-display-month class="calendar-display">{yyyy}</a>', '<div class="calendar-arrow">', '<span class="prev" data-calendar-arrow-month>{prev}</span>', '<span class="next" data-calendar-arrow-month>{next}</span>', "</div>", "</div>", '<ol class="calendar-ct month-items">{month}</ol>', "</div>", "</div>", "</div>", '<div class="calendar-label"><p>HelloWorld</p><i></i></div>'],
    V = Object.prototype.toString;
  String.prototype.repeat = function (t) {
    return this.replace(/\{\w+\}/g, function (e) {
      var a = e.replace(/\{|\}/g, "");
      return t[a] || ""
    })
  }, String.prototype.toDate = function () {
    var t = (new Date, this.replace(/\d/g, "").charAt(0)),
      e = this.split(t);
    return new Date(parseInt(e[0]), parseInt(e[1]) - 1, parseInt(e[2]))
  }, Date.prototype.format = function (t) {
    var e = this.getFullYear(),
      a = this.getMonth() + 1,
      i = this.getDate();
    return t.replace("yyyy", e).replace("mm", a).replace("dd", i)
  }, Date.prototype.isSame = function (t, a, i) {
    if (e(t)) {
      var n = t;
      t = n.getFullYear(), a = n.getMonth() + 1, i = n.getDate()
    }
    return this.getFullYear() === t && this.getMonth() + 1 === a && this.getDate() === i
  }, Date.prototype.add = function (t) {
    this.setDate(this.getDate() + t)
  }, Date.prototype.minus = function (t) {
    this.setDate(this.getDate() - t)
  }, Date.prototype.clearTime = function (t) {
    return this.setHours(0), this.setSeconds(0), this.setMinutes(0), this.setMilliseconds(0), this
  }, Date.isLeap = function (t) {
    return t % 100 !== 0 && t % 4 === 0 || t % 400 === 0
  }, Date.getDaysNum = function (t, e) {
    var a = 31;
    switch (e) {
      case 2:
        a = this.isLeap(t) ? 29 : 28;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        a = 30
    }
    return a
  }, Date.getSiblingsMonth = function (t, e, a) {
    var i = new Date(t, e - 1);
    return i.setMonth(e - 1 + a), {
      y: i.getFullYear(),
      m: i.getMonth() + 1
    }
  }, Date.getPrevMonth = function (t, e, a) {
    return this.getSiblingsMonth(t, e, 0 - (a || 1))
  }, Date.getNextMonth = function (t, e, a) {
    return this.getSiblingsMonth(t, e, a || 1)
  }, Date.tryParse = function (t) {
    return t ? e(t) ? t : t.toDate() : t
  }, n.prototype = {
    constructor: n,
    getDayAction: function (t) {
      var e = c;
      if (this.selectedRang) {
        var a = Date.tryParse(this.selectedRang[0]),
          i = Date.tryParse(this.selectedRang[1]);
        (a && t < a.clearTime() || i && t > i.clearTime()) && (e = p)
      }
      return e
    },
    getDayData: function (t) {
      var e, a = this.data;
      if (a)
        for (var i = 0, n = a.length; i < n; i++) {
          var s = a[i];
          t.isSame(Date.tryParse(s.date)) && (e = s.value)
        }
      return e
    },
    getDayItem: function (e, a, i, n) {
      var s, o, r = this.date,
        h = new Date(e, a - 1, i),
        l = {
          w: this.width / 7,
          h: this.height / 7,
          value: i,
        };
      r.isSame(e, a, i) ? w : "";
      return 1 === n ? l.class = g : 3 === n ? l.class = v : 5 === n ? l.class = 'isClass' : l.class = "", r.isSame(e, a, i) && (l.class += " " + D), l.date = h.format(this.options.format), l.action = this.getDayAction(h), s = this.getDayData(h), o = t(b.repeat(l)), s && (o.data(m, s), o.html(i + y)), o
    },
    getDaysHtml: function (a, i) {
      var n, s, o, r, h, l, d = (this.date, t('<ol class="days"></ol>'));
      e(a) ? (n = a.getFullYear(), s = a.getMonth() + 1) : (n = Number(a), s = Number(i)), o = new Date(n, s - 1, 1).getDay() || 7, l = o - this.options.startWeek, r = Date.getDaysNum(n, s), h = Date.getPrevMonth(n, s), prevDaysNum = Date.getDaysNum(n, h.m), nextM = Date.getNextMonth(n, s);
      for (var c = 1, u = 2, p = 3, m = 0, f = prevDaysNum - l + 1; f <= prevDaysNum; f++, m++){
        if(changeDate(h.y+"/"+h.m+"/"+f)){
          d.append(this.getDayItem(h.y, h.m, f, 5));
        }else{
          d.append(this.getDayItem(h.y, h.m, f, c));
        };
      } 
      for (var g = 1; g <= r; g++, m++){
        if(changeDate(n+"/"+s+"/"+g)){
          d.append(this.getDayItem(n, s, g, 5));
        }else{
           d.append(this.getDayItem(n, s, g, u));
        };
      } 
      for (var v = 1, D = 42 - m; v <= D; v++){
        if(changeDate(nextM.y+"/"+nextM.m+"/"+v)){
          d.append(this.getDayItem(nextM.y, nextM.m, v, 5));
        }else{
          d.append(this.getDayItem(nextM.y, nextM.m, v, p));
        };
      } 
      return t("<li></li>").width(this.options.width).append(d)
    },
    getWeekHtml: function () {
      for (var t = [], e = this.options.weekArray, a = this.options.startWeek, i = e.length, n = this.width / 7, s = this.height / 7, o = a; o < i; o++) t.push(x.repeat({
        w: n,
        h: s,
        wk: e[o]
      }));
      for (var r = 0; r < a; r++) t.push(x.repeat({
        w: n,
        h: s,
        wk: e[r]
      }));
      return t.join("")
    },
    getMonthHtml: function () {
      for (var t = this.options.monthArray, e = [], a = this.width / 4, i = this.height / 4, n = 0; n < 12; n++) e.push(I.repeat({
        w: a,
        h: i,
        m: t[n]
      }));
      return e.join("")
    },
    setMonthAction: function (t) {
      var e = this.date.getMonth() + 1;
      this.$monthItems.children().removeClass(D), t === this.date.getFullYear() && this.$monthItems.children().eq(e - 1).addClass(D)
    },
    fillStatic: function () {
      var t = {
        prev: this.options.prev,
        next: this.options.next,
        week: this.getWeekHtml(),
        month: this.getMonthHtml()
      };
      this.$element.html(k.join("").repeat(t))
    },
    updateDisDate: function (t, e) {
      this.$disDate.html(M.repeat({
        year: t,
        month: e
      }))
    },
    updateDisMonth: function (t) {
      this.$disMonth.html(t)
    },
    fillDateItems: function (t, e) {
      getDateList();
      var a = [Date.getPrevMonth(t, e), {
        y: t,
        m: e
      }, Date.getNextMonth(t, e)];
      this.$dateItems.html("");
      for (var i = 0; i < 3; i++) {
        var n = this.getDaysHtml(a[i].y, a[i].m);
        this.$dateItems.append(n)
      }
    },
    hide: function (t, e, a) {
      this.$trigger.val(e.format(this.options.format)), this.options.onClose.call(this, t, e, a), this.$element.hide()
    },
    setPosition: function () {
      var t = this.$trigger.offset(),
        e = this.options.offset;
      this.$element.css({
        left: t.left + e[0] + "px",
        top: t.top + this.$trigger.outerHeight() + e[1] + "px"
      })
    },
    trigger: function () {
      this.$trigger = t(this.options.trigger);
      var e = this,
        a = e.$element;
      a.addClass("calendar-modal").css("zIndex", e.options.zIndex), t(document).click(function (i) {
        e.$trigger[0] == i.target || t.contains(a[0], i.target) || a.hide()
      }).on("click", this.options.trigger, function () {
        this.$trigger = t(this), e.setPosition(), a.show()
      }), t(window).resize(function () {
        e.setPosition()
      })
    },
    render: function () {
      this.$week = this.$element.find(".week"), this.$dateItems = this.$element.find(".date-items"), this.$monthItems = this.$element.find(".month-items"), this.$label = this.$element.find(".calendar-label"), this.$disDate = this.$element.find(r), this.$disMonth = this.$element.find(h);
      var t = this.date.getFullYear(),
        e = this.date.getMonth() + 1;
      this.updateDisDate(t, e), this.updateMonthView(t), this.fillDateItems(t, e), this.options.trigger && this.trigger()
    },
    setView: function (t) {
      this.$element.removeClass(f.date + " " + f.month).addClass(f[t]), this.view = t
    },
    updateDateView: function (e, a, i, n) {
      getDateList();
      a = a || this.date.getMonth() + 1;
      var s = this,
        o = this.$dateItems,
        r = {
          prev: function () {
            var i = Date.getPrevMonth(e, a),
              r = Date.getPrevMonth(e, a, 2),
              h = s.getDaysHtml(r.y, r.m);
            a = i.m, e = i.y, o.animate({
              marginLeft: 0
            }, 300, "swing", function () {
              o.children(":last").remove(), o.prepend(h).css("margin-left", "-100%"), t.isFunction(n) && n.call(s)
            })
          },
          next: function () {
            var i = Date.getNextMonth(e, a),
              r = Date.getNextMonth(e, a, 2),
              h = s.getDaysHtml(r.y, r.m);
            a = i.m, e = i.y, o.animate({
              marginLeft: "-200%"
            }, 300, "swing", function () {
              o.children(":first").remove(), o.append(h).css("margin-left", "-100%"), t.isFunction(n) && n.call(s)
            })
          }
        };
      return i ? r[i]() : this.fillDateItems(e, a), this.updateDisDate(e, a), this.setView("date"), {
        y: e,
        m: a
      }
    },
    updateMonthView: function (t) {
      this.updateDisMonth(t), this.setMonthAction(t), this.setView("month")
    },
    getDisDateValue: function () {
      var t = this.$disDate.html().split("/"),
        e = Number(t[0]),
        a = Number(t[1].match(/\d{1,2}/)[0]);
      return [e, a]
    },
    selectedDay: function (t, e) {
      var a = this.getDisDateValue(),
        i = a[0],
        n = a[1],
        s = function () {
          this.$dateItems.children(":eq(1)").find("[" + c + "]:not(." + v + ", ." + g + ")").removeClass(w).filter(function (e) {
            return parseInt(this.innerHTML) === t
          }).addClass(w)
        };
      if (e) {
        var o = this.updateDateView(i, n, {
          old: "prev",
          new: "next"
        } [e], s);
        i = o.y, n = o.m, this.options.viewChange("date", i, n)
      } else s.call(this);

      return new Date(i, n - 1, t)
    },
    showLabel: function (t, e, a, i) {
      var n = this.$label;
      n.find("p").html(this.options.label.repeat({
        m: e,
        d: a.format(this.options.format),
        v: i
      }).replace(/\n/g, "<br>"));
      var s = n.outerWidth(),
        o = n.outerHeight();
      n.css({
        left: t.pageX - s / 2 + "px",
        top: t.pageY - o - 20 + "px",
        zIndex: this.options.zIndex + 1
      }).show()
    },
    hasLabel: function () {
      return !!this.options.label && (t("body").append(this.$label), !0)
    },
    event: function () {
      var e = this,
        a = e.options.viewChange;
      e.$element.on("click", r, function () {
        var t = e.getDisDateValue();
        e.updateMonthView(t[0], t[1]), a("month", t[0], t[1])
      }).on("click", h, function () {
        var t = this.innerHTML;
        e.updateDateView(t), a("date", t)
      }), e.$element.on("click", l, function () {
        var t = e.getDisDateValue(),
          n = i(this),
          s = t[0],
          o = t[1],
          r = e.updateDateView(s, o, n, function () {
            a("date", r.y, r.m)
          })
      }).on("click", d, function () {
        var t = Number(e.$disMonth.html()),
          n = i(this);
        t = "prev" === n ? t - 1 : t + 1, e.updateMonthView(t), a("month", t)
      }), e.$element.on("click", "[" + c + "]", function () {
        let className = this.className;
        if(className.includes('isClass')){
          getclassList(this.getAttribute('data-calendar-day'));
        }
        var a = parseInt(this.innerHTML),
          n = i(this),S
          s = /new|old/.test(n) ? n.match(/new|old/)[0] : "",
          o = e.selectedDay(a, s);
        e.options.onSelected.call(this, "date", o, t(this).data(m)), e.$trigger && e.hide("date", o, t(this).data(m))
      }).on("click", "[" + u + "]", function () {
        var i = Number(e.$disMonth.html()),
          n = t(this).index() + 1;
        e.updateDateView(i, n), a("date", i, n), e.options.onSelected.call(this, "month", new Date(i, n - 1))
      }), e.$element.on("mouseenter", "[" + c + "]", function (a) {
        var i = t(this),
          n = i.attr(c).toDate();
        e.hasLabel() && i.data(m) && e.showLabel(a, "date", n, i.data(m)), e.options.onMouseenter.call(this, "date", n, i.data(m))
      }).on("mouseleave", "[" + c + "]", function () {
        e.$label.hide()
      })
    },
    resize: function () {
      var t = this.width,
        e = this.height,
        a = this.$element.find(".calendar-hd").outerHeight();
      this.$element.width(t).height(e + a).find(".calendar-inner, .view").css("width", t + "px"), this.$element.find(".calendar-ct").width(t).height(e)
    },
    init: function () {
      this.fillStatic(), this.resize(), this.render(), this.view = this.options.view, this.setView(this.view), this.event()
    },
    setData: function (t) {
      if (this.data = t, "date" === this.view) {
        var e = this.getDisDateValue();
        this.fillDateItems(e[0], e[1])
      } else "month" === this.view && this.updateMonthView(this.$disMonth.html())
    },
    setDate: function (t) {
      var e = Date.tryParse(t);
      this.updateDateView(e.getFullYear(), e.getMonth() + 1), this.selectedDay(e.getDate())
    },
    methods: function (t, e) {
      if ("[object Function]" === V.call(this[t])) return this[t].apply(this, e)
    }
  }, t.fn.calendar = function (e) {
    var i, s = this.data("calendar"),
      o = [].slice.call(arguments);
    return s ? a(e) ? (i = e, o.shift(), s.methods(i, o)) : this : this.each(function () {
      return t(this).data("calendar", new n(this, e))
    })
  }, t.fn.calendar.defaults = s
});

$('#calendar').calendar({
  weekArray: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
  monthArray: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'],
  selectedRang: null,
  view: 'date',
});