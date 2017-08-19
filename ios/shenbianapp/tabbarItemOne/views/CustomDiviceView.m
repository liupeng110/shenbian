//
//  CustomDiviceView.m
//  Miaohi
//
//  Created by 杨绍智 on 16/12/6.
//  Copyright © 2016年 haiqiu. All rights reserved.
//

#import "CustomDiviceView.h"
@implementation CustomDiviceView

- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor whiteColor];
        [self addSubview:self.seachView];
        [self.seachView addSubview:self.seachIcon];
        [self addSubview:self.navBarScroll];
    }
    return self;
}
- (void)setCustomDiviceViewByArray:(NSArray*)lableArray andCureentObject:(NSString*)kind_name{
    CGFloat lasetBtnW =20;
    CGFloat spaceW = 20;
    NSArray * viewArray =self.navBarScroll.subviews;
    for (UIView * view in viewArray) {
        [view removeFromSuperview];
    }
    //先计算当前屏幕一屏能否展示开
     for (int i = 0; i < lableArray.count; i++) {
         RootObjectModel * lablemodel =lableArray[i];
         //根据文字长度适当增加按钮宽度,按照最大文字字体增大
         NSDictionary *titleTtributes = @{NSFontAttributeName:[UIFont boldSystemFontOfSize:15.0]};
         CGFloat titleLength = [[NSString stringWithFormat:@"%@",lablemodel.object_name] boundingRectWithSize:CGSizeMake(self.frame.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:titleTtributes context:nil].size.width;
         lasetBtnW = lasetBtnW+titleLength+20;
     }
    if (lasetBtnW<=kScreenWidth) {
        spaceW = 20+((kScreenWidth-lasetBtnW)/5.0);
    }else{
        spaceW = 20;
    }
    lasetBtnW = 20;
    [self.navBarScroll addSubview:self.lineLabel];
    self.MhSquareCollectionArray = [[NSMutableArray alloc]initWithArray:lableArray];
    for (int i = 0; i < lableArray.count; i++) {
        LXButton *btn = [LXButton buttonWithType:UIButtonTypeRoundedRect];
        btn.tag =  100+i;
        [btn addTarget:self action:@selector(clickAction:) forControlEvents:UIControlEventTouchUpInside];
        RootObjectModel * lablemodel =lableArray[i];
        //根据文字长度适当增加按钮宽度,按照最大文字字体增大
        NSDictionary *titleTtributes = @{NSFontAttributeName:[UIFont boldSystemFontOfSize:15.0]};
        CGFloat titleLength = [[NSString stringWithFormat:@"%@",lablemodel.object_name] boundingRectWithSize:CGSizeMake(self.frame.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:titleTtributes context:nil].size.width;
        btn.frame = CGRectMake(lasetBtnW, 2, titleLength, 26);
        
        if (i==lableArray.count-1) {
           lasetBtnW = lasetBtnW+titleLength+20;
        }else{
           lasetBtnW = lasetBtnW+titleLength+spaceW;
        }
        [btn setTitle:lablemodel.object_name forState:UIControlStateNormal];
        [self.navBarScroll addSubview:btn];
        if ([lablemodel.object_name isEqualToString:kind_name]) {
            //btn.titleLabel.font = [UIFont boldSystemFontOfSize:15.0];
            //[btn setTitleColor:[UIColor colorWithHexColor:@"#ffffff"] forState:UIControlStateNormal];
            self.lineLabel.centerX = btn.centerX;
            self.lineLabel.frame = CGRectMake(btn.x-2, 28, btn.width+4, 2);

        }else{
            btn.titleLabel.font = [UIFont systemFontOfSize:14.0];
        }
        [btn setTitleColor:[UIColor colorWithHexColor:@"#666666"] forState:UIControlStateNormal];
        btn.titleLabel.font = [UIFont systemFontOfSize:14.0];
    }
    self.navBarScroll.contentSize = CGSizeMake(lasetBtnW, 30);
    self.contOffSetLeng = lasetBtnW;
}

- (UIImageView*)seachIcon{
    if (!_seachIcon) {
        _seachIcon = [[UIImageView alloc]initWithFrame:CGRectMake(8, 2, 15, 15)];
        _seachIcon.image = [UIImage imageNamed:@"sousuokuangfangdajiang"];
    }
    return _seachIcon;
}
- (UIView*)seachView{
    if (!_seachView) {
        _seachView = [[UIView alloc]initWithFrame:CGRectMake(15, 15, kScreenWidth-30, 19)];
        _seachView.backgroundColor = [UIColor colorWithHexColor:@"#dfdfdf"];
        _seachView.clipsToBounds = YES;
        _seachView.layer.cornerRadius = 19.0/2.0;
    }
    return _seachView;
}
- (UIScrollView*)navBarScroll{
    if (!_navBarScroll) {
        _navBarScroll = [[UIScrollView alloc]initWithFrame:CGRectMake(0, 40, kScreenWidth, 30)];
        _navBarScroll.showsVerticalScrollIndicator = NO;
        _navBarScroll.showsHorizontalScrollIndicator = NO;
        _navBarScroll.scrollsToTop = NO;
        _navBarScroll.bounces = NO;
        _navBarScroll.backgroundColor = [UIColor whiteColor];
    }
    return _navBarScroll;
}

- (UILabel*)lineLabel{
    if (!_lineLabel) {
        _lineLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 28, 35, 2)];
        _lineLabel.backgroundColor = [UIColor colorWithHexColor:@"#00a0e9"];
    }
    return _lineLabel;
}

- (void)clickAction:(LXButton*)sender{
    NSArray * viewArray =self.navBarScroll.subviews;
    for (UIView * view in viewArray) {
        if ([view isKindOfClass:[LXButton class]]) {
            LXButton * tem = (LXButton*)view;
            if (tem.tag==sender.tag) {
                [self setBtnValue:sender.tag-100];
                if (self.delegate && [self.delegate respondsToSelector:@selector(CustomDiviceViewClickIndex:)]) {
                    [self.delegate CustomDiviceViewClickIndex:sender.tag-100];
              }
            }
        }
    }
    
}
- (void)setBtnValue:(NSInteger)index{
    
    NSArray * viewArray =self.navBarScroll.subviews;
    for (UIView * view in viewArray) {
        if ([view isKindOfClass:[LXButton class]]) {
            LXButton * tem = (LXButton*)view;
            if (tem.tag==index+100) {
                //[tem setTitleColor:[UIColor colorWithHexColor:@"#ffffff"] forState:UIControlStateNormal];
                //tem.titleLabel.font = [UIFont boldSystemFontOfSize:15.0];
                self.lineLabel.centerX = tem.centerX;
                self.lineLabel.frame = CGRectMake(tem.x-2, 28, tem.width+4, 2);
            }else{
                //tem.titleLabel.font = [UIFont systemFontOfSize:14.0];
                //[tem setTitleColor:[UIColor colorWithHexColor:@"#999999"] forState:UIControlStateNormal];
            }
        }
    }
    
    if (self.contOffSetLeng>kScreenWidth) {
        //当前点击的按钮是否超出屏幕范围
        LXButton * curentBtn = (LXButton*)[self.navBarScroll viewWithTag:index+100];
        LXButton * nextBtn = (LXButton*)[self.navBarScroll viewWithTag:index+100+1];//当前点击的下一个
        if (nextBtn) {
            
        }else{
            //[UIView animateWithDuration:0.3 animations:^{
                self.navBarScroll.contentOffset = CGPointMake(self.contOffSetLeng-self.navBarScroll.width, 0);
            //}];
            return;
        }
        
        float spaceLength =nextBtn.x+nextBtn.width+20-self.navBarScroll.width;
        if (spaceLength>0) {
            if (curentBtn.x+curentBtn.width+20-((kScreenWidth-curentBtn.width)/2.0)-20+self.navBarScroll.width>self.contOffSetLeng) {
                //[UIView animateWithDuration:0.3 animations:^{
                    self.navBarScroll.contentOffset = CGPointMake(self.contOffSetLeng-self.navBarScroll.width, 0);
                //}];
            }else{
                //[UIView animateWithDuration:0.3 animations:^{
                    self.navBarScroll.contentOffset = CGPointMake(curentBtn.x+curentBtn.width+20-((kScreenWidth-curentBtn.width)/2.0)-20, 0);
                //}];
                
            }
        }else{
            // 最前面一屏
            //[UIView animateWithDuration:0.3 animations:^{
                self.navBarScroll.contentOffset = CGPointMake(0, 0);
            //}];
            
        }

    }
    
    
}
@end
